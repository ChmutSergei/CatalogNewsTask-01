package by.chmut.catalog.controller.command;


import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.News;
import by.chmut.catalog.bean.criteria.Criteria;
import by.chmut.catalog.bean.criteria.SearchCriteria;
import by.chmut.catalog.controller.Command;
import by.chmut.catalog.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.chmut.catalog.constant.Constant.MAIN;


public class SearchCommand implements Command {

    private Criteria<SearchCriteria.Category> categoryCriteria = new Criteria<>(SearchCriteria.Category.class);
    private Criteria<SearchCriteria.Subcategory> subcategoryCriteria = new Criteria<>(SearchCriteria.Subcategory.class);
    private Criteria<SearchCriteria.News> newsCriteria = new Criteria<>(SearchCriteria.News.class);

    private static final ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        makeCriteriaWithReceivedParams(req);

        HttpSession session = req.getSession();

        Catalog catalog = (Catalog) session.getAttribute("catalog");

        List<News> result = catalog.getAllNews();

        result = factory.getService().find(categoryCriteria, result);

        result = factory.getService().find(subcategoryCriteria, result);

        result = factory.getService().find(newsCriteria, result);

        session.setAttribute("result", result);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(MAIN);

        requestDispatcher.forward(req, resp);

        removeInformationMessages(session);
    }



    private void makeCriteriaWithReceivedParams(HttpServletRequest req) {
        String categoryName = req.getParameter("category");
        String subcategoryName = req.getParameter("subcategory");
        String newsName = req.getParameter("newsName");
        String newsProvider = req.getParameter("newsProvider");
        String newsDate = req.getParameter("newsDate");
        String newsBody = req.getParameter("newsBody");
        categoryCriteria.add(SearchCriteria.Category.CATEGORYNAME, categoryName);
        subcategoryCriteria.add(SearchCriteria.Subcategory.SUBCATEGORYNAME, subcategoryName);
        newsCriteria.add(SearchCriteria.News.NEWSNAME, newsName);
        newsCriteria.add(SearchCriteria.News.PROVIDER, newsProvider);
        newsCriteria.add(SearchCriteria.News.DATE, newsDate);
        newsCriteria.add(SearchCriteria.News.NEWS, newsBody);

    }

    private void removeInformationMessages(HttpSession session) {
        session.setAttribute("error","");
        session.setAttribute("success","");
    }


}
