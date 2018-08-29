package by.chmut.catalog.controller.command;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static by.chmut.catalog.bean.criteria.SearchCriteria.News.*;
import static by.chmut.catalog.bean.criteria.SearchCriteria.Category.*;
import static by.chmut.catalog.bean.criteria.SearchCriteria.Subcategory.*;
import static by.chmut.catalog.controller.command.constant.Constant.MAIN;


public class SearchCommand implements Command {

    private Criteria<SearchCriteria.Category> categoryCriteria = new Criteria<>(SearchCriteria.Category.class);
    private Criteria<SearchCriteria.Subcategory> subcategoryCriteria = new Criteria<>(SearchCriteria.Subcategory.class);
    private Criteria<SearchCriteria.News> newsCriteria = new Criteria<>(SearchCriteria.News.class);

    private static final ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession();

        List<Criteria> allCriteriaToSearchNews = makeCriteriaWithReceivedParams(req);

        Set<News> result = factory.getService().find(allCriteriaToSearchNews);

        session.setAttribute("result", result);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(MAIN);

        requestDispatcher.forward(req, resp);

        removeInformationMessages(session);
    }



    private List<Criteria> makeCriteriaWithReceivedParams(HttpServletRequest req) {
        categoryCriteria.add(CATEGORYNAME, req.getParameter("category"));
        subcategoryCriteria.add(SUBCATEGORYNAME, req.getParameter("subcategory"));
        newsCriteria.add(NEWSNAME, req.getParameter("newsName"));
        newsCriteria.add(PROVIDER, req.getParameter("newsProvider"));
        newsCriteria.add(DATE, req.getParameter("newsDate"));
        newsCriteria.add(NEWS, req.getParameter("newsBody"));
        List<Criteria> listOfCriteriaToSearch = new ArrayList<>();
        listOfCriteriaToSearch.add(categoryCriteria);
        listOfCriteriaToSearch.add(subcategoryCriteria);
        listOfCriteriaToSearch.add(newsCriteria);
        return listOfCriteriaToSearch;
    }

    private void removeInformationMessages(HttpSession session) {
        session.setAttribute("error","");
        session.setAttribute("success","");
    }


}
