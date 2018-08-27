package by.chmut.catalog.controller.command;

import by.chmut.catalog.controller.Command;
import by.chmut.catalog.service.Service;
import by.chmut.catalog.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddCommand implements Command {
    private static final String[] options = {"category", "subcategory", "newsName", "body", "date", "provider"};
    private static final List<String> template = new ArrayList<>(Arrays.asList(options));

    public String[] sexecute(String request) {
//        String[] resultAdd = new String[1];
//        String[] data = parseReqestForDataNews(request);
//        Service service = new ServiceImpl();
//        service.addNews(data);
//        resultAdd[0] = "Ok";
        return null;//resultAdd;
    }

    private String[] parseReqestForDataNews(String request) {
        List<String> data = new ArrayList<String>(Arrays.asList(request.split("-")));
        List<String> result = new ArrayList<String>();
        for (String param: data) {
            String[] keyValue = param.trim().split("=",2);
            result = addDataNews(keyValue, result);
        }
        return result.toArray(new String[0]);
    }

    private List<String> addDataNews(String[] keyValue, List<String> data) {
        String keyFromRequest  = keyValue[0].trim();
        String valueForKeyFromRequest  = keyValue[1].trim();
        for (String key:template) {
            if (key.equalsIgnoreCase(keyFromRequest)) {
                data.add(template.indexOf(key), valueForKeyFromRequest);
            }
        }
        return data;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    }
}
