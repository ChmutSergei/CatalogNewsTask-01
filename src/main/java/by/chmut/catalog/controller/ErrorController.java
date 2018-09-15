package by.chmut.catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ErrorController {

    @RequestMapping(value = "/error")

    public String error(ModelMap model){

        model.addAttribute("error", "ERROR with load or save catalog");

        return "/main";
    }
}
