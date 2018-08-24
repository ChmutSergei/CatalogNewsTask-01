package by.chmut.catalog.bean;

import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;


@NoArgsConstructor
@XmlRootElement(name="CATALOG")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {

    public Catalog(List<Category> categories) {
        this.categories = categories;
    }

    @XmlElement(name = "CATEGORY")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public Category returnIfCategoryExist(String categoryToSearch) {
        int index = -1;
        for (Category category : categories) {
            if (category.getName().equals(categoryToSearch))
                index = categories.indexOf(category);
        }
        if (index != -1) {
            return categories.remove(index);
        }
        return null;
    }
}

