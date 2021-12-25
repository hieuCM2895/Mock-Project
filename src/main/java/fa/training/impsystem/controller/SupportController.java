package fa.training.impsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SupportController {

    @RequestMapping("/")
    public String checkStatus() {
        return "status";
    }

}
