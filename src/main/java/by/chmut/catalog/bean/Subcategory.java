package by.chmut.catalog.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Subcategory extends Category {


    public Subcategory(String categoryName, String subcategoryName) {
        super(categoryName);
        this.subcategoryName = subcategoryName;
    }
    @XmlElement(name = "SUBCATEGORY_NAME")
    private String subcategoryName;

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcatName) {
        this.subcategoryName = subcatName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subcategory that = (Subcategory) o;
        return Objects.equals(subcategoryName, that.subcategoryName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), subcategoryName);
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "subcategoryName='" + subcategoryName + '\'' +
                '}';
    }
}