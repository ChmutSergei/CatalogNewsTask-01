package by.chmut.catalog.controller.command;

import by.chmut.catalog.controller.Command;
import by.chmut.catalog.dao.CatalogDAO;
import by.chmut.catalog.dao.CatalogDAOImpl;
import by.chmut.catalog.dao.DAOException;
import by.chmut.catalog.service.Session;

public class SaveCommand implements Command {
    @Override
    public String[] execute(String request) {
        Session session = new Session();
        CatalogDAO catalogDAO = new CatalogDAOImpl();
        try {
            catalogDAO.saveAll(session.getCatalog());
        } catch (DAOException e) {
            System.out.println("Error with save news");
        }
        return null;
    }
}
