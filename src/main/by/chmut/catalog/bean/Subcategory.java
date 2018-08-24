package by.chmut.catalog.bean;


import by.chmut.catalog.bean.News;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;


@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Subcategory {
    public Subcategory(String name, List<News> news) {
        this.name = name;
        this.news = news;
    }

    @XmlElement(name = "SUBCATEGORY_NAME")
    private String name;

    @XmlElement(name = "NEWS")
    private List<News> news;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
    public void addNews(News newNews) {
        this.news.add(newNews);
    }
}
