package by.chmut.catalog.service;

import by.chmut.catalog.bean.News;
import by.chmut.catalog.bean.criteria.Criteria;

import java.util.List;


public interface Service {

    <E> List<News> find(Criteria<E> criteria, List<News> news);

}
