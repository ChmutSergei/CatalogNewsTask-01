package by.chmut.catalog.bean;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class News {
    public News(String name, String provider, String date, String news) {
        this.name = name;
        this.provider = provider;
        this.date = date;
        this.news = news;
    }

    @XmlElement(name = "NEWS_NAME")
    private String name;

    @XmlElement(name = "PROVIDER_AUTHOR_AUTHORS")
    private String provider;

    @XmlElement(name = "DATE_OF_ISSUE")
    private String date;

    @XmlElement(name = "NEWS_BODY")
    private String news;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
