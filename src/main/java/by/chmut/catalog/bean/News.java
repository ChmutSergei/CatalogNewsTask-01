package by.chmut.catalog.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)

public class News extends Subcategory{


    @XmlElement(name = "NEWS_NAME")
    private String nameNews;

    @XmlElement(name = "PROVIDER_AUTHOR_AUTHORS")
    private String provider;

    @XmlElement(name = "DATE_OF_ISSUE")
    private String date;

    @XmlElement(name = "NEWS_BODY")
    private String body;

    public News(String categoryName, String subcategoryName, String nameNews, String provider, String date, String body) {
        super(categoryName, subcategoryName);
        this.nameNews = nameNews;
        this.provider = provider;
        this.date = date;
        this.body = body;

    }

    public News(String subcategoryName, String nameNews, String provider, String date, String body) {
        super("", subcategoryName);
        this.nameNews = nameNews;
        this.provider = provider;
        this.date = date;
        this.body = body;

    }

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        News news = (News) o;
        return Objects.equals(nameNews, news.nameNews) &&
                Objects.equals(provider, news.provider) &&
                Objects.equals(date, news.date) &&
                Objects.equals(body, news.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), nameNews, provider, date, body);
    }

    @Override
    public String toString() {
        return "News{" +
                "name='" + nameNews + '\'' +
                ", provider='" + provider + '\'' +
                ", date='" + date + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
