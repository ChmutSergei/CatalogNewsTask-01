package by.chmut.catalog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AddController {

    @GetMapping(value = "/add")
    public String mainPage(ModelMap model) {

        return "/add";
    }

}
