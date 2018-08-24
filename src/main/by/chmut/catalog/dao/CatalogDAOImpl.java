package by.chmut.catalog.dao;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.Category;
import by.chmut.catalog.bean.News;
import by.chmut.catalog.bean.Subcategory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;

public class CatalogDAOImpl implements CatalogDAO{


//    public static void main(String[] args) throws Exception {

//        News news = new News("Michael Jackson the best","No name","20.06.2018","New album M.Jackson");
//        Subcategory subcategory = new Subcategory("Disk", news);
//        List<Subcategory> subcategories = new ArrayList<>();
//        subcategories.add(subcategory);
//        Category category = new Category("Music", subcategories);
//        List<Category> categories = new ArrayList<>();
//        categories.add(category);
//        Catalog catalog = new Catalog(categories);




//        JAXBContext jc = JAXBContext.newInstance(Catalog.class);
//
//        Unmarshaller unmarshaller = jc.createUnmarshaller();
//        Catalog catalog = (Catalog) unmarshaller.unmarshal(new File("src/com/lectures/catalog/input.xml"));
//        List<Category> categories = catalog.getCategories();
//        for (Category category : categories) {
//            System.out.println("Category :"+category.getName());
//            List<Subcategory> subcategories = category.getSubcategories();
//            for (Subcategory subcategory: subcategories) {
//                System.out.println("Subcategory name:"+subcategory.getName());
//                News news = subcategory.getNews();
//                System.out.println("News name:"+news.getName());
//                System.out.println("Provider:"+news.getProvider());
//                System.out.println("Date:"+news.getDate());
//                System.out.println("Body:"+news.getNews());
//            }
//        }
//        Marshaller marshaller = jc.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(catalog, System.out);
//    }

    @Override
    public Catalog readAll() throws DAOException {
        Catalog catalog = new Catalog();
        try {
            JAXBContext jc = JAXBContext.newInstance(Catalog.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            catalog = (Catalog) unmarshaller.unmarshal(new File("src/main/java/by/chmut/catalog/bean/catalog.xml"));
        } catch (JAXBException e) {
            throw new DAOException("Error with read file catalog", e);
        }
        return catalog;
    }

    @Override
    public void saveAll(Catalog catalog) throws DAOException {
        try {
//            File file = new File("src/by/htp/catalog/catalog.xml");
            JAXBContext jc = JAXBContext.newInstance(Catalog.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(catalog, new File("src/main/java/by/chmut/catalog/bean/catalog.xml"));
        } catch (JAXBException e) {
            throw new DAOException("Error with save file catalog", e);
        }
    }
}


