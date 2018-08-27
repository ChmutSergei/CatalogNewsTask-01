package by.chmut.catalog.dao;

import by.chmut.catalog.bean.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;

public class JAXBparserImpl implements JAXBparser {


    @Override
    public Catalog load() throws DAOException{
        Catalog catalog = new Catalog();
        try {
            JAXBContext jc = JAXBContext.newInstance(Catalog.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            catalog = (Catalog) unmarshaller.unmarshal(new File("D:/workspace/CatalogNews/src/main/java/by/chmut/catalog/bean/catalog.xml"));
        } catch (JAXBException e) {
            throw new DAOException("Error with read file catalog", e);
        }
        return catalog;
    }


    @Override
    public void save(Catalog catalog2) throws DAOException {
        try {
            JAXBContext jc = JAXBContext.newInstance(Catalog.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(catalog2, new File("D:/workspace/CatalogNews/src/main/java/by/chmut/catalog/bean/catalog.xml"));
        } catch (JAXBException e) {
            throw new DAOException("Error with save file catalog", e);
        }
    }

}
