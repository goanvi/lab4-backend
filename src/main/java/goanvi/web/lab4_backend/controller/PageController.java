package goanvi.web.lab4_backend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController implements ErrorController {

    @GetMapping({"/","/home","/login", "/error"})
    public String getPage(){
        return "index.html";
    }
}
