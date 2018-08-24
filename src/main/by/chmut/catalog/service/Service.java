package by.chmut.catalog.service;

public interface Service {
    String[] getNewsOnValue(String value, String paramOnSearch);
    void addNews (String[] data);
}
