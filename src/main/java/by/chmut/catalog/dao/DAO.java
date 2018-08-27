package by.chmut.catalog.dao;


import by.chmut.catalog.bean.News;
import by.chmut.catalog.bean.criteria.Criteria;

import java.util.List;

public interface DAO {

    <E> List<News> find(Criteria<E> criteria, List<News> news);

}
