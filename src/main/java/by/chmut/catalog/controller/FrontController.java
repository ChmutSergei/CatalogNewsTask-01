package by.chmut.catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class FrontController {

    @GetMapping(value = "/")
    public String mainPage() {

        return "redirect:/read";
    }

}
