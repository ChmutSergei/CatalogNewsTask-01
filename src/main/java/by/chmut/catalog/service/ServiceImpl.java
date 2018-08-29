package by.chmut.catalog.service;

import by.chmut.catalog.bean.*;
import by.chmut.catalog.bean.criteria.Criteria;
import by.chmut.catalog.dao.DAOFactory;
import by.chmut.catalog.service.validation.Validator;

import java.util.List;
import java.util.Set;


public class ServiceImpl implements Service {

    private static final DAOFactory factory = DAOFactory.getInstance();

    @Override
    public <E> Set<News> find(List<Criteria> allCriteriaToSearch) {

        Set<News> result = factory.getCatalogDAO().getAllNews();

        for (Criteria criteria: allCriteriaToSearch) {

            if (Validator.criteriaIsNotEmpty(criteria)) {

                result = factory.getCatalogDAO().find(criteria, result);

            }
        }

        return result;
    }
}
