package by.chmut.catalog.service.validation;

import by.chmut.catalog.bean.criteria.Criteria;

import java.util.Map;

public class Validator {

    public static <E> boolean criteriaValidator(Criteria<E> criteria) {
        for (Map.Entry entry: criteria.getCriteria().entrySet()) {
            if (entry.getValue() != null) {
                return true;
            }
        }
        return false;
    }
}
