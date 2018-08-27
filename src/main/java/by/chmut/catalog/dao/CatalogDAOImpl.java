package by.chmut.catalog.dao;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.Categories;
import by.chmut.catalog.bean.News;
import by.chmut.catalog.bean.criteria.Criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogDAOImpl implements CatalogDAO{


    @Override
    public <E> Catalog find(Criteria<E> criteria, Catalog catalog) {

        Map<E, Object> thisCriteria = new HashMap<E, Object>();

        thisCriteria = criteria.getCriteria();
        List<News> resultNews = new ArrayList<>();

        for (Map.Entry<E,Object> entry:thisCriteria.entrySet()) {
            for (Categories category: catalog.getCategories()) {
                for (News news: category.getNews() ) {
                    Criteria tempCriteria = (Criteria) entry;
                    if (tempCriteria.getGroupSearchName().getSimpleName().equals("CATEGORYNAME")) {
                        if (isFieldContainsValue(news.getCategoryName(),(String)entry.getValue())) {
                            resultNews.add(news);
                        }
                    }

                    if (tempCriteria.getGroupSearchName().getSimpleName().equals("SUBCATEGORYNAME")) {
                        if (isFieldContainsValue(news.getSubcategoryName(),(String)entry.getValue())) {
                            resultNews.add(news);
                        }
                    }

                    if (tempCriteria.getGroupSearchName().getSimpleName().equals("NEWSNAME")) {
                        if (isFieldContainsValue(news.getNameNews(),(String)entry.getValue())) {
                            resultNews.add(news);
                        }
                    }

                    if (tempCriteria.getGroupSearchName().getSimpleName().equals("PROVIDER")) {
                        if (isFieldContainsValue(news.getProvider(),(String)entry.getValue())) {
                            resultNews.add(news);
                        }
                    }
                    if (tempCriteria.getGroupSearchName().getSimpleName().equals("DATE")) {
                        if (isFieldContainsValue(news.getDate(),(String)entry.getValue())) {
                            resultNews.add(news);
                        }
                    }
                    if (tempCriteria.getGroupSearchName().getSimpleName().equals("NEWS")) {
                        if (isFieldContainsValue(news.getBody(),(String)entry.getValue())) {
                            resultNews.add(news);
                        }
                    }
                }

            }

        }
        Categories result = new Categories("search",resultNews);
        List<Categories> list = new ArrayList<>();
        list.add(result);
        Catalog results = new Catalog();
        results.setCategories(list);
        return catalog;
    }

    private boolean isFieldContainsValue(String field, String value) {
        String valueLowerCase = value.toLowerCase();
        String valueFirstCharUpperCase = firstUpperCase(value);
        String valueUpperCase = value.toUpperCase();
        if (field.contains(valueLowerCase) || field.contains(valueFirstCharUpperCase) || field.contains(valueUpperCase)) {
            return true;
        }
        return false;
    }

    private String firstUpperCase(String value) {
        if (value == null || value.isEmpty()) return "";
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

}


