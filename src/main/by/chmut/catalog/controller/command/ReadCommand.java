package by.chmut.catalog.controller.command;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.controller.Command;
import by.chmut.catalog.dao.CatalogDAO;
import by.chmut.catalog.dao.CatalogDAOImpl;
import by.chmut.catalog.dao.DAOException;
import by.chmut.catalog.service.Session;

public class ReadCommand implements Command {

    @Override
    public String[] execute(String request) {
        CatalogDAO catalogDAO = new CatalogDAOImpl();
        try {
            Catalog catalog = catalogDAO.readAll();
            Session session = new Session();
            session.setCatalog(catalog);
        } catch (DAOException e) {
            System.out.println("Error with read news");
        }
        return new String[0];
    }
}
