package by.chmut.catalog.controller;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.News;
import by.chmut.catalog.dao.DAOException;
import by.chmut.catalog.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/controller")

public class Controller extends HttpServlet {

    private final CommandDirector commandDirector = new CommandDirector();
    private final DAOFactory daoFactory = DAOFactory.getInstance();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();
        News news = new News("dfw", "wdfev", "wefev", "dfvew", "efwe", "wvew");
        List<News> ne = new ArrayList<>();
        ne.add(news);
        session.setAttribute("news", ne);
        setCatalogInSessionIfDoesNotSet(session);

        String command = (String) req.getAttribute("command");

        commandDirector.getCommand(command).execute(req, resp);


    }
    private void setCatalogInSessionIfDoesNotSet(HttpSession session) {
        if (session.getAttribute("catalog") == null) {
            try {
                Catalog catalog = daoFactory.getParser().load();
                session.setAttribute("catalog", catalog);
            } catch (DAOException e) {
                session.setAttribute("error", "Error with Load catalog");
            }
        }

    }
}
