package by.chmut.catalog.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorCommand implements by.chmut.catalog.controller.Command {

    public String[] sexecute(String request) {
        String[] result = new String[1];
        result[0] = "Incorrect command or no data in the command! Try again!";
        return result;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    }
}
