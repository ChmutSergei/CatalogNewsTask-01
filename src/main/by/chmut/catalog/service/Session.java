package by.chmut.catalog.service;

import by.chmut.catalog.bean.Catalog;

public class Session {
    private static Catalog catalog;

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        Session.catalog = catalog;
    }
}
