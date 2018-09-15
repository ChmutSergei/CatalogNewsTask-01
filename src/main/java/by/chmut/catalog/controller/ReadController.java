package by.chmut.catalog.controller;

import by.chmut.catalog.service.Service;
import by.chmut.catalog.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReadController {

    @Autowired
    private Service service;

    @GetMapping(value = "/read")
    public String mainPage(ModelMap model) {
        try {
            service.load();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:/main";
    }
}
