package by.chmut.catalog.controller.command;

import by.chmut.catalog.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.chmut.catalog.controller.Constant.MAIN;

public class MainCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(MAIN);

        requestDispatcher.forward(req,resp);
    }
}
