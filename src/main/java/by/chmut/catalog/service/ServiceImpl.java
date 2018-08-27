package by.chmut.catalog.service;

import by.chmut.catalog.bean.*;
import by.chmut.catalog.bean.criteria.Criteria;
import by.chmut.catalog.dao.CatalogDAO;
import by.chmut.catalog.dao.DAOFactory;
import by.chmut.catalog.service.validation.Validator;


public class ServiceImpl implements Service {
    private static final int COUNT_CHAR_MAX = 85;
    private final DAOFactory factory = DAOFactory.getInstance();

    @Override
    public <E> Catalog find(Criteria<E> criteria, Catalog catalog) {
        if (!Validator.criteriaValidator(criteria)) {
            return catalog;
        }

        CatalogDAO catalogDAO = factory.getCatalogDAO();

        catalog = (Catalog) catalogDAO.find(criteria, catalog);

        return catalog;
    }
}
