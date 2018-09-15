package by.chmut.catalog.service;

import by.chmut.catalog.bean.*;
import by.chmut.catalog.bean.criteria.Criteria;
import by.chmut.catalog.dao.CatalogDAO;
import by.chmut.catalog.dao.DAOException;
import by.chmut.catalog.service.validation.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ServiceImpl implements Service {

    ServiceImpl() {
    }

    private CatalogDAO catalogDAO;

    public ServiceImpl(CatalogDAO catalogDAO) {
        this.catalogDAO = catalogDAO;
    }

    @Override
    public <E> Set<News> find(List<Criteria> allCriteriaToSearch) throws ServiceException {

        Set<News> result = new HashSet<>();

        for (Criteria criteria : allCriteriaToSearch) {

            if (Validator.isNotEmpty(criteria)) {

                try {
                    result.addAll(catalogDAO.find(criteria));
                } catch (DAOException e) {
                    throw new ServiceException(e);
                }

            }
        }

        return result;
    }

    @Override
    public void addNews(News news) {

        catalogDAO.add(news);

    }

    @Override
    public Catalog load() throws ServiceException {
        Catalog catalog = null;
        try {
            catalog = catalogDAO.load();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return catalog;
    }

    @Override
    public void save() throws ServiceException {
        try {
            catalogDAO.save();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }


    }
}
