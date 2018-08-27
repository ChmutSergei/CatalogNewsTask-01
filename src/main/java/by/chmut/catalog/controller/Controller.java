package by.chmut.catalog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet (urlPatterns = "/controller")

public class Controller extends HttpServlet {

    private final CommandDirector commandDirector = new CommandDirector();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String command = req.getParameter("command");

        commandDirector.getCommand(command).execute(req, resp);


    }
}
