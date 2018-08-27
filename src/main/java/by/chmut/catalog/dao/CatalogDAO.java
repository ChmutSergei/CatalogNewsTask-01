package by.chmut.catalog.dao;


import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.Category;
import by.chmut.catalog.bean.criteria.Criteria;

public interface CatalogDAO {

    <E> Catalog find(Criteria<E> criteria, Catalog catalog);

}
