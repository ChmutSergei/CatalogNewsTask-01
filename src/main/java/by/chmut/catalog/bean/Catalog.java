package by.chmut.catalog.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@XmlRootElement(name = "CATALOG")
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

    @XmlElement(name = "CATEGORY")
    private List<Categories> categories;

    public Catalog(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();
        for (Categories categories: categories) {
            for (News oneNews:categories.getNews()) {
                news.add(oneNews);
            }
        }
        return news;
    }

}