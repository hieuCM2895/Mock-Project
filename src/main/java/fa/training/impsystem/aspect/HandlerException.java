package fa.training.impsystem.aspect;

import fa.training.impsystem.exception.EmailHadRegistered;
import fa.training.impsystem.exception.EmailNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@ControllerAdvice(basePackages = "training.fpt.com")
public class HandlerException implements ErrorController {

    /**
     *
     * Function to handle exception EmailHadRegistered
     */
    @ExceptionHandler(EmailHadRegistered.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView handleEmailException(Exception ex) {

        ModelAndView model = new ModelAndView("Login");
        model.addObject("info", "Email Had Registered.");
        model.addObject("error", "1");
        return model;
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleUserNameNotFoundException(Exception ex) {

        ModelAndView model = new ModelAndView("Login");
        model.addObject("error", "1");
        model.addObject("info", ex.getMessage());
        return model;
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleEmailNotFoundException(Exception ex) {

        ModelAndView model = new ModelAndView("ForgotPassword");
        model.addObject("error", ex.getMessage());
        return model;
    }

    @ExceptionHandler(value = {MessagingException.class, UnsupportedEncodingException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleSendEmailException(Exception ex) {

        ModelAndView model = new ModelAndView("ForgotPassword");
        model.addObject("error", "Error When Sending Email " + ex.getMessage());
        return model;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("Exception");
        model.addObject("status", "HTTP 500 Internal Server error");
        return model;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {

        ModelAndView model = new ModelAndView("Exception");
        model.addObject("status", "Pages can't found 404");
        return model;
    }

}
