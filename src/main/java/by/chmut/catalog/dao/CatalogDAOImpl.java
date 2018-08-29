package by.chmut.catalog.dao;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.News;
import by.chmut.catalog.bean.criteria.Criteria;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static by.chmut.catalog.bean.criteria.SearchCriteria.News.*;
import static by.chmut.catalog.bean.criteria.SearchCriteria.Category.*;
import static by.chmut.catalog.bean.criteria.SearchCriteria.Subcategory.*;

public class CatalogDAOImpl implements CatalogDAO {

    private Catalog catalog;
    private Set<News> resultSearch;
    private Set<News> newsForSearch;

    public Catalog getCatalog() {
        return catalog;
    }

    @Override
    public <E> Set<News> find(Criteria<E> criteria, Set<News> newsForSearch) {

        resultSearch = new HashSet<>();

        this.newsForSearch = newsForSearch;

        for (Map.Entry<E, Object> parameterOfCriteria : criteria.getCriteria().entrySet()) {

            if (parameterOfCriteria.getValue() != "") {


                News news = findNewsOnParameterCriteria3(parameterOfCriteria.getKey(), parameterOfCriteria.getValue());

                if (news != null) {

                    resultSearch.add(news);
                }
//                News news = findNewsOnParameterCriteria(parameterOfCriteria.getKey(), parameterOfCriteria.getValue());
//
//                if (news != null) {
//
//                    resultSearch.add(news);
//                }
            }
        }
        return resultSearch;
    }

    private <E> News findNewsOnParameterCriteria3(E nameParameterForSearch, Object valueOfParameter)  {
        String fieldValue = "";
        for (News news : newsForSearch) {

            try {
                Field field = News.class.getDeclaredField(nameParameterForSearch.toString()); // Get private field from News class
                field.setAccessible(true); // Set Access for this field
                fieldValue = (String) field.get(news); // And take value from current Object ! news !
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            if (isFieldContainsValue(fieldValue, (String) valueOfParameter)) {

                return news;

            }
        }
        return null;
    }


    private <E> News findNewsOnParameterCriteria(E nameParameterForSearch, Object valueOfParameter) {

        for (News news : newsForSearch) {

            String newsFieldValue = getValueFieldOnParameterName2(nameParameterForSearch, news);

            if (isFieldContainsValue(newsFieldValue, (String) valueOfParameter)) {

                return news;

            }
        }
        return null;
    }

    private <E> String getValueFieldOnParameterName2(E nameParameterForSearch, News news) {
        String value = null;
        for (Method method : News.class.getMethods()) {
            for (Annotation annotation : method.getAnnotations())
                if (annotation.equals(nameParameterForSearch)) {
                    try {
                        value = (String) method.invoke(news);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
        }
        return value;
    }


    private <E> String getValueFieldOnParameterName(E nameParameterForSearch, News news) {
        String value = null;
        if (nameParameterForSearch == CATEGORYNAME) {
            value = news.getCategoryName();
        }
        if (nameParameterForSearch == SUBCATEGORYNAME) {
            value = news.getSubcategoryName();
        }
        if (nameParameterForSearch == NEWSNAME) {
            value = news.getNameNews();
        }
        if (nameParameterForSearch == PROVIDER) {
            value = news.getProvider();
        }
        if (nameParameterForSearch == DATE) {
            value = news.getDate();
        }
        if (nameParameterForSearch == NEWS) {
            value = news.getBody();
        }
        return value;
    }

    private boolean isFieldContainsValue(String field, String value) {

        if (value.isEmpty()) {
            return false;
        }

        boolean ifFieldContainValueLowerCase = field.contains(value.toLowerCase());

        boolean ifFieldContainValueWithFirstCharUpperCase = field.contains(firstUpperCase(value));

        boolean ifFieldContainValueUpperCase = field.contains(value.toUpperCase());

        if (ifFieldContainValueLowerCase || ifFieldContainValueUpperCase || ifFieldContainValueWithFirstCharUpperCase) {

            return true;
        }

        return false;

    }

    private String firstUpperCase(String value) {

        value = value.toLowerCase();

        if (value.isEmpty()) {
            return "";
        }

        value = value.substring(0, 1).toUpperCase() + value.substring(1);

        return value;
    }

    @Override
    public Catalog load() throws DAOException {

        JAXBparser jaxbParser = new JAXBparser();

        catalog = jaxbParser.load();

        return catalog;
    }

    @Override
    public void save(Catalog catalog) throws DAOException {

        JAXBparser jaxbParser = new JAXBparser();

        jaxbParser.save(catalog);

    }

    @Override
    public Set<News> getAllNews() {

        Set<News> allNews = catalog.getAllNews();

        return allNews;
    }

}


