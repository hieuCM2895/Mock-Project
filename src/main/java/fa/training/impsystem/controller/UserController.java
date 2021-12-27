package fa.training.impsystem.controller;

import fa.training.impsystem.dto.UserDTO;
import fa.training.impsystem.exception.EmailHadRegistered;
import fa.training.impsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserDTO userDTO() {
        return new UserDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "Register";
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<String> listOfError = new ArrayList<>();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                listOfError.add(error.getDefaultMessage());
            }
            model.addAttribute("listE", listOfError);
            return "Register";

        } else {
            try {
                boolean result = userService.save(userDTO);
                if (result) {
                    return "/login?success";
                }
            } catch (EmailHadRegistered ex) {
                model.addAttribute("inf", ex.getMessage());
                return "Register";
            }
        }
        return "login";
    }

}
