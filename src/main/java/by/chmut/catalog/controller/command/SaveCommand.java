package by.chmut.catalog.controller.command;

import by.chmut.catalog.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveCommand implements Command {

    public String[] sexecute(String request) {
//        Session session = new Session();
//        CatalogDAO catalogDAO = new CatalogDAOImpl();
//        try {
//            catalogDAO.saveAll(session.getCatalog());
//        } catch (DAOException e) {
//            System.out.println("Error with save body");
//        }
        return null;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    }
}
