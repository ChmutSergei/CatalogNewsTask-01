package by.chmut.catalog.dao;

import by.chmut.catalog.bean.News;
import by.chmut.catalog.bean.criteria.Criteria;

import java.util.*;

import static by.chmut.catalog.bean.criteria.SearchCriteria.News.*;
import static by.chmut.catalog.bean.criteria.SearchCriteria.Category.*;
import static by.chmut.catalog.bean.criteria.SearchCriteria.Subcategory.*;

public class DAOImpl implements DAO {


    @Override
    public <E> List<News> find(Criteria<E> criteria, List<News> news) {

        Map<E, Object> thisCriteria = criteria.getCriteria();

        Set<News> resultNews = new HashSet<>();

        for (Map.Entry<E, Object> entry : thisCriteria.entrySet()) {
            for (News oneNews : news) {
                if (entry.getKey() == CATEGORYNAME) {
                    if (isFieldContainsValue(oneNews.getCategoryName(), (String) entry.getValue())) {
                        resultNews.add(oneNews);
                    }
                }

                if (entry.getKey() == SUBCATEGORYNAME) {
                    if (isFieldContainsValue(oneNews.getSubcategoryName(), (String) entry.getValue())) {
                        resultNews.add(oneNews);
                    }
                }

                if (entry.getKey() == NEWSNAME) {
                    if (isFieldContainsValue(oneNews.getNameNews(), (String) entry.getValue())) {
                        resultNews.add(oneNews);
                    }
                }

                if (entry.getKey() == PROVIDER) {
                    if (isFieldContainsValue(oneNews.getProvider(), (String) entry.getValue())) {
                        resultNews.add(oneNews);
                    }
                }
                if (entry.getKey() == DATE) {
                    if (isFieldContainsValue(oneNews.getDate(), (String) entry.getValue())) {
                        resultNews.add(oneNews);
                    }
                }
                if (entry.getKey() == NEWS) {
                    if (isFieldContainsValue(oneNews.getBody(), (String) entry.getValue())) {
                        resultNews.add(oneNews);
                    }
                }
            }
        }
        return new ArrayList<>(resultNews);
    }

    private boolean isFieldContainsValue(String field, String value) {
        String valueLowerCase = value.toLowerCase();
        String valueFirstCharUpperCase = firstUpperCase(value);
        String valueUpperCase = value.toUpperCase();
        return field.contains(valueLowerCase) || field.contains(valueFirstCharUpperCase) || field.contains(valueUpperCase);
    }

    private String firstUpperCase(String value) {
        value = value.toLowerCase();
        if (value.isEmpty()) return "";
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

}


