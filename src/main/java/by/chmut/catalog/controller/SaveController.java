package by.chmut.catalog.controller;

import by.chmut.catalog.bean.News;

import by.chmut.catalog.service.Service;
import by.chmut.catalog.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SaveController {

    @Autowired
    private Service service;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam(value = "category") String category, @RequestParam(value = "subcategory") String subcategory,
                       @RequestParam(value = "newsName") String newsName, @RequestParam(value = "newsProvider") String newsProvider,
                       @RequestParam(value = "newsDate") String newsDate, @RequestParam(value = "newsBody") String newsBody,
                       ModelMap model) {

        News freshNews = new News(category, subcategory, newsName, newsProvider, newsDate, newsBody);

        service.addNews(freshNews);

        try {
            service.save();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        model.addAttribute("success", "New Information successfully Added!");

        return "/main";
    }


}
