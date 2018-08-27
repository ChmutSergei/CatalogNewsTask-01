package by.chmut.catalog.bean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TEST {

    public static void main(String[] args) {
        News news =new News("Films","action","Mission","chmut","24.06.2018","new film of Mission Impossible 6");
        News news2 =new News("Films","action","Rocky","chmut","24.06.2010","new film of Rocky Balboa");
        News news3 =new News("Films","other","Drama","chmut","24.06.2011","new film of Jane Eir");
        News news4 =new News("Music","other","Jackson","chmut","24.06.2011","new album of Mike Jack");
        News news5 =new News("Music","pop","Sia","chmut","24.06.2014","new album of Sia");
        News news6 =new News("Music","rock","Marlin","chmut","24.06.2012","new album of Marlin Manson");
        News news7 =new News("Books","roman","Dostoevskiy","chmut","24.09.2010","new book Dostoevskiy F.M.");
        News news8 =new News("Books","since","Java","chmut","24.09.205","new book Java HeadFirst");
        List<News> any = new ArrayList<>();
        any.add(news);
        any.add(news2);
        any.add(news3);
        List<News> any2 = new ArrayList<>();
        any2.add(news4);
        any2.add(news5);
        any2.add(news6);
        List<News> any3 = new ArrayList<>();
        any3.add(news7);
        any3.add(news8);
        Categories films = new Categories(news.getCategoryName(),any);
        Categories music = new Categories(news4.getCategoryName(),any2);
        Categories books = new Categories(news7.getCategoryName(),any3);
        List<Categories> categories = new ArrayList();
        categories.add(films);
        categories.add(music);
        categories.add(books);
        Catalog catalog = new Catalog(categories);

        try {
            JAXBContext jc = JAXBContext.newInstance(Catalog.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(catalog, new File("D:/workspace/CatalogNews/src/main/java/by/chmut/catalog/bean/catalog2.xml"));


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
