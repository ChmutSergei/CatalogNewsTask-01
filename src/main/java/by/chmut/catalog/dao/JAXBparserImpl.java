package by.chmut.catalog.dao;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.Categories;
import by.chmut.catalog.bean.News;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class JAXBparserImpl implements JAXBparser {

    @Override
    public Catalog load() throws DAOException{
        try {
            JAXBContext jc = JAXBContext.newInstance(Catalog.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Catalog catalog = (Catalog) unmarshaller.unmarshal(new File("D:/workspace/CatalogNews/src/main/resources/catalog.xml"));
            setCategoryNameForAllNews(catalog);
            return catalog;
        } catch (JAXBException e) {
            throw new DAOException("Error with read file catalog", e);
        }
    }


    @Override
    public void save(Catalog catalog) throws DAOException {
        try {
            JAXBContext jc = JAXBContext.newInstance(Catalog.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(catalog, new File("D:/workspace/CatalogNews/src/main/resources/catalog.xml"));
        } catch (JAXBException e) {
            throw new DAOException("Error with save file catalog", e);
        }
    }

    private void setCategoryNameForAllNews(Catalog catalog) {
        for (Categories categories:catalog.getCategories()) {
            for (News news:categories.getNews()) {
                news.setCategoryName(categories.getCategoryName());
            }
        }
    }
}
