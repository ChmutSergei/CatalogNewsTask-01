package by.chmut.catalog.bean;

import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {
    public Category(String name, List<Subcategory> subcategories) {
        this.name = name;
        this.subcategories = subcategories;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "CATEGORY_NAME")
    private String name;

    @XmlElement(name = "SUBCATEGORY")
    private List<Subcategory> subcategories;

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
    public void addSubcategory(Subcategory subcategory) {
        this.subcategories.add(subcategory);
    }
    public Subcategory returnIfSubcategoryExist(String subcategoryToSearch) {
        int index = -1;
        for (Subcategory subcategory : subcategories) {
            if (subcategory.getName().equals(subcategoryToSearch))
                index = subcategories.indexOf(subcategory);
        }
        if (index != -1) {
            return subcategories.remove(index);
        }
        return null;
    }
}
