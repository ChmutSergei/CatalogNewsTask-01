package by.chmut.catalog.service;

import by.chmut.catalog.bean.Catalog;
import by.chmut.catalog.bean.Category;
import by.chmut.catalog.bean.News;
import by.chmut.catalog.bean.Subcategory;

import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service {
    private static final int COUNT_CHAR_MAX = 85;

    @Override
    public String[] getNewsOnValue(String value, String paramOnSearch) {
        List<String> allResult = new ArrayList<String>();
        Session session = new Session();
        Catalog catalog = session.getCatalog();
        List<Category> categories = catalog.getCategories();
        for (Category category : categories) {
            List<Subcategory> subcategories = category.getSubcategories();
            for (Subcategory subcategory : subcategories) {
                List<News> allNews = subcategory.getNews();
                for (News news : allNews) {
                    if (paramOnSearch.equals("name") && isFieldContainsValue(news.getName(), value)
                            || paramOnSearch.equals("date") && isFieldContainsValue(news.getDate(), value)
                            || paramOnSearch.equals("body") && isFieldContainsValue(news.getNews(), value)) {
                        allResult.addAll(makeResult(news, subcategory.getName(), category.getName()));
                    }
                }
            }
        }
        return allResult.toArray(new String[0]);
    }

    private boolean isFieldContainsValue(String field, String value) {
        String valueLowerCase = value.toLowerCase();
        String firstUpperCaseValue = firstUpperCase(value);
        String valueUpperCase = value.toUpperCase();
        if (field.contains(valueLowerCase) || field.contains(firstUpperCaseValue) || field.contains(valueUpperCase)) {
            return true;
        }
        return false;
    }

    private String firstUpperCase(String value) {
        if (value == null || value.isEmpty()) return "";
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }


    private List<String> makeResult(News news, String subcategory, String category) {
        List<String> oneNews = new ArrayList<>();
        oneNews.add("\u001B[31mCategory : \u001B[0m" + category);
        oneNews.add("\u001B[31mSubcategory : \u001B[0m" + subcategory);
        oneNews.add("\u001B[31mNews name: \u001B[0m" + news.getName());
        oneNews.add("\u001B[31mNews date: \u001B[0m" + news.getDate());
        oneNews.add("\u001B[31mNews provider: \u001B[0m" + news.getProvider());
        oneNews.add("\u001B[31mNews :\u001B[0m");
        oneNews.addAll(splitStringOnLength(news.getNews()));
        return oneNews;
    }

    private List<String> splitStringOnLength(String string) {
        String words[] = string.split(" ");
        ArrayList<String> lines = new ArrayList<String>();

        StringBuilder stringBuffer = new StringBuilder();
        int countChars = 0;
        int indexOfWord = 0;
        int countWords = words.length;

        while (indexOfWord != countWords) {
            if (countChars + words[indexOfWord].length() <= COUNT_CHAR_MAX) {
                countChars += words[indexOfWord].length() + 1;
                stringBuffer.append(words[indexOfWord]).append(" ");
                indexOfWord++;
            } else {
                lines.add(stringBuffer.toString());
                stringBuffer = new StringBuilder();
                countChars = 0;
            }
        }

        if (stringBuffer.length() > 0) {
            lines.add(stringBuffer.toString());
        }
        return lines;
    }

    @Override
    public void addNews(String[] data) {
        Session session = new Session();
        Catalog catalog = session.getCatalog();
        News newNews = new News();
        newNews.setName(data[2]);
        newNews.setNews(data[3]);
        newNews.setDate(data[4]);
        newNews.setProvider(data[5]);
        Category category = catalog.returnIfCategoryExist(data[0]);
        if (category != null) {
            Subcategory subcategory = category.returnIfSubcategoryExist(data[1]);
            if (subcategory != null) {
                subcategory.addNews(newNews);
                category.addSubcategory(subcategory);
                catalog.addCategory(category);
            } else {
                List<News> news = new ArrayList<>();
                subcategory = new Subcategory(data[1], news);
                subcategory.addNews(newNews);
                category.addSubcategory(subcategory);
                catalog.addCategory(category);
            }
        } else {
            catalog.addCategory(createNewCategoryWithSubcategoryAndNews(data[0],data[1],newNews,catalog));
        }
    }

    private Category createNewCategoryWithSubcategoryAndNews(String nameCategory, String nameSubcategory,
                                                             News newNews, Catalog catalog) {
        List<News> news = new ArrayList<>();
        List<Subcategory> subcategories = new ArrayList<>();
        Category category = new Category(nameCategory,subcategories);
        catalog.addCategory(category);
        Subcategory subcategory = new Subcategory(nameSubcategory, news);
        subcategory.addNews(newNews);
        category.addSubcategory(subcategory);
        catalog.addCategory(category);
        return category;
    }

}
