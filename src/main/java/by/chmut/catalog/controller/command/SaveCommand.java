package by.chmut.catalog.controller.command;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.Categories;
import by.chmut.catalog.bean.News;
import by.chmut.catalog.controller.Command;
import by.chmut.catalog.dao.DAOException;
import by.chmut.catalog.dao.DAOFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class SaveCommand implements Command {

    private Catalog catalog;
    private final DAOFactory daoFactory = DAOFactory.getInstance();

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        News freshNews = makeNewsWithReceivedParams(req);

        catalog = (Catalog) req.getSession().getAttribute("catalog");

        checkCategoryForFreshNewsAndAddToCategory(freshNews);

        saveCatalog();

        req.getSession().setAttribute("success","New Information successfully Added!");

        String contextPath = req.getContextPath();

        resp.sendRedirect(contextPath+ "/controller?command=main");

    }


    private News makeNewsWithReceivedParams(HttpServletRequest req) {
        String categoryName = req.getParameter("category");
        String subcategoryName = req.getParameter("subcategory");
        String newsName = req.getParameter("newsName");
        String newsProvider = req.getParameter("newsProvider");
        String newsDate = req.getParameter("newsDate");
        String newsBody = req.getParameter("newsBody");
        return new News(categoryName, subcategoryName, newsName, newsProvider, newsDate, newsBody);
    }

    private void checkCategoryForFreshNewsAndAddToCategory(News freshNews) {
        for (Categories categories:catalog.getCategories()) {
            if (categories.getCategoryName().equalsIgnoreCase(freshNews.getCategoryName())) {
                categories.add(freshNews);
                return;
            }
        }
        Categories newCategories = new Categories(freshNews.getCategoryName(),new ArrayList<News>());
        newCategories.getNews().add(freshNews);
        catalog.getCategories().add(newCategories);
    }

    private void saveCatalog() {
        try {
            daoFactory.getParser().save(catalog);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
