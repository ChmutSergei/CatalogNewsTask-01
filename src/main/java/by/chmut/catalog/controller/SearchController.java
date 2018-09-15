package by.chmut.catalog.controller;

import by.chmut.catalog.bean.News;
import by.chmut.catalog.bean.criteria.Criteria;
import by.chmut.catalog.bean.criteria.SearchCriteria;
import by.chmut.catalog.service.Service;
import by.chmut.catalog.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static by.chmut.catalog.bean.criteria.SearchCriteria.News.*;
import static by.chmut.catalog.bean.criteria.SearchCriteria.Category.*;
import static by.chmut.catalog.bean.criteria.SearchCriteria.Subcategory.*;


@Controller
public class SearchController {
    @Autowired
    private Service service;

    private Criteria<SearchCriteria.Category> categoryCriteria = new Criteria<>(SearchCriteria.Category.class);
    private Criteria<SearchCriteria.Subcategory> subcategoryCriteria = new Criteria<>(SearchCriteria.Subcategory.class);
    private Criteria<SearchCriteria.News> newsCriteria = new Criteria<>(SearchCriteria.News.class);

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam(value = "category") String category, @RequestParam(value = "subcategory") String subcategory,
                         @RequestParam(value = "newsName") String newsName, @RequestParam(value = "newsProvider") String newsProvider,
                         @RequestParam(value = "newsDate") String newsDate, @RequestParam(value = "newsBody") String newsBody,
                         ModelMap model) {

        List<Criteria> allCriteriaToSearchNews = makeCriteriaWithReceivedParams(category, subcategory, newsName, newsProvider, newsDate, newsBody);

        Set<News> result = null;
        try {
            result = service.find(allCriteriaToSearchNews);
        } catch (ServiceException e) {

        }

        model.addAttribute("result", result);

        return "/main";
    }

    private List<Criteria> makeCriteriaWithReceivedParams(String category, String subcategory, String newsName, String newsProvider, String newsDate, String newsBody) {
        categoryCriteria.add(CATEGORYNAME, category);
        subcategoryCriteria.add(SUBCATEGORYNAME, subcategory);
        newsCriteria.add(NEWSNAME, newsName);
        newsCriteria.add(PROVIDER, newsProvider);
        newsCriteria.add(DATE, newsDate);
        newsCriteria.add(NEWS, newsBody);
        List<Criteria> listOfCriteriaToSearch = new ArrayList<>();
        listOfCriteriaToSearch.add(categoryCriteria);
        listOfCriteriaToSearch.add(subcategoryCriteria);
        listOfCriteriaToSearch.add(newsCriteria);
        return listOfCriteriaToSearch;
    }


}
