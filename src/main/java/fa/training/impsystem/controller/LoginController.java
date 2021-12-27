package fa.training.impsystem.controller;

import fa.training.impsystem.consts.RegexConst;
import fa.training.impsystem.dto.UserDTO;
import fa.training.impsystem.exception.EmailNotFoundException;
import fa.training.impsystem.service.impl.UserServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/login")
    public String login() throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "Login";
        } else {
            return "redirect:/home";
        }

    }

    @PostMapping("/login")
    public ModelAndView loginPost(@RequestParam(required = false) String message) {

        ModelAndView model = new ModelAndView("Login");

        if (message != null && !message.isEmpty()) {
            if (message.equals("max_session")) {
                model.addObject("error", "1");
                model.addObject("info", "This account has been login from another device!");
            }
        }
        return model;

    }

    @PostMapping("/fail")
    public ModelAndView failLoginUserName(@RequestParam(name = "email") String email) {

        ModelAndView model = new ModelAndView("Login");

        UserDTO userDTO = userService.findUserByEmail(email);
        model.addObject("error", "1");
        if (userDTO == null) {
            model.addObject("info", "Email Not Exits");
        } else {
            if (userDTO.getFailedAttempt() < UserServiceImpl.MAX_FAILED_ATTEMPTS - 1) {
                int limitLock = UserServiceImpl.MAX_FAILED_ATTEMPTS - userDTO.getFailedAttempt() - 1;
                model.addObject("info", "Password Not Correct, The Number Of Remaining Logins is " + limitLock);
            } else {
                model.addObject("info", "Your account has been locked due to 10 failed attempts. It will be unlocked after 24 hours");
            }
        }
        return model;

    }

    @GetMapping("/forgot-password")
    public String showForgetPasswordForm() {
        return "ForgotPassword";
    }

    @PostMapping("/forgot-password")
    public String processForgetPasswordForm(@RequestParam(name = "email") String email, Model model, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException, EmailNotFoundException {

        String token = RandomString.make(45);

        userService.updateResetPasswordToken(token, email);
        String siteURL = request.getRequestURL().toString();
        String resetPasswordLink = siteURL.replace(request.getServletPath(), "").concat("/reset-password?token=").concat(token);

        sendEmail(email, resetPasswordLink);
        model.addAttribute("error", "We have sent a reset password link to your email. Please check.");

        return "ForgotPassword";
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@Param(value = "token") String token, Model model) {

        if (token == null) {
            model.addAttribute("error", "Please Send Token In Link Your Email");
            return "ForgotPassword";
        } else {
            UserDTO userDTO = userService.getByResetPasswordToken(token);
            if (userDTO == null) {
                model.addAttribute("error", "Invalid Token");
                return "ForgotPassword";
            }
            model.addAttribute("token", token);
            model.addAttribute("user", new UserDTO());

            return "ResetPassword";
        }

    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam(value = "token") String token,
                                       @RequestParam(value = "new-password") String newPassword, Model model) {

        if (token == null) {
            model.addAttribute("error", "Please Send Token In Link Your Email");
            return "ForgotPassword";
        } else {
            UserDTO userDTO = userService.getByResetPasswordToken(token);

            if (userDTO == null) {
                model.addAttribute("message", "Invalid Token");
                return "ResetPassword";
            } else if (Pattern.compile(RegexConst.REGEX_PASSWORD).matcher(newPassword).matches()) {
                boolean result = userService.updatePassword(userDTO, newPassword);
                if (result) {
                    model.addAttribute("error", 1);
                    model.addAttribute("info", "You have successfully changed your password.");
                }
            }
            return "login";
        }

    }

    private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("Hieu2895@gmail.com", "HieuCM2 Support");
        helper.setTo(email);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        javaMailSender.send(message);

    }

    @GetMapping("/home")
    public String homePage() {
        return "Home";
    }

    @PostMapping("/home")
    public String homePageSuccessLogin() {
        return "Home";
    }

}
