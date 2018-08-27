package by.chmut.catalog.service;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.criteria.Criteria;



public interface Service {

    <E> Catalog find(Criteria<E> criteria, Catalog catalog);

}
