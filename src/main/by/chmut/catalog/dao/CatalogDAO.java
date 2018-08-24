package by.chmut.catalog.dao;

import by.chmut.catalog.bean.Catalog;

public interface CatalogDAO {
    Catalog readAll() throws DAOException;
    void saveAll(Catalog catalog) throws DAOException;
}
