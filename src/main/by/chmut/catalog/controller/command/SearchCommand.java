package by.chmut.catalog.controller.command;

import by.chmut.catalog.controller.Command;
import by.chmut.catalog.service.Service;
import by.chmut.catalog.service.ServiceImpl;


public class SearchCommand implements Command {
    @Override
    public String[] execute(String request) {
        String[] reqParam = request.split("=",2);
        Service service = new ServiceImpl();
        String paramOnSearch = reqParam[0];
        String value = reqParam[1];
        return service.getNewsOnValue(value.trim(), paramOnSearch.trim());
    }
}
