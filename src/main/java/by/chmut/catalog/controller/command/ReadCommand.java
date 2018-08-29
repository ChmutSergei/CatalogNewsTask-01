package by.chmut.catalog.controller.command;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.controller.Command;
import by.chmut.catalog.dao.DAOException;
import by.chmut.catalog.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReadCommand implements Command {

    private static final DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();

        setCatalogInSessionIfDoesNotSet(session);

        String contextPath = req.getContextPath();

        resp.sendRedirect(contextPath+ "/controller?command=main");

    }



    private void setCatalogInSessionIfDoesNotSet(HttpSession session) {
        if (session.getAttribute("catalog") == null) {
            try {
                Catalog catalog = daoFactory.getCatalogDAO().load();
                session.setAttribute("catalog", catalog);
            } catch (DAOException e) {
                session.setAttribute("error", "Error with Load catalog");
            }
        }
    }
}
