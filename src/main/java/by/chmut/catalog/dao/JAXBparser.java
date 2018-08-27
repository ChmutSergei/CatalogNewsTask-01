package by.chmut.catalog.dao;

import by.chmut.catalog.bean.Catalog;

public interface JAXBparser {

    Catalog load() throws DAOException;

    void save (Catalog catalog) throws DAOException;

}
