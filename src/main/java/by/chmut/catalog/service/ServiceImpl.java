package by.chmut.catalog.service;

import by.chmut.catalog.bean.*;
import by.chmut.catalog.bean.criteria.Criteria;
import by.chmut.catalog.dao.DAO;
import by.chmut.catalog.dao.DAOFactory;
import by.chmut.catalog.service.validation.Validator;

import java.util.List;


public class ServiceImpl implements Service {

    private final DAOFactory factory = DAOFactory.getInstance();

    @Override
    public <E> List<News> find(Criteria<E> criteria, List<News> news) {

        if (!Validator.criteriaValidator(criteria)) {
            return news;
        }

        DAO DAO = factory.getDAO();

        return DAO.find(criteria, news);
    }
}
