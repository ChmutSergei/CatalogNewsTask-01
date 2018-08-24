package by.chmut.catalog.controller.command;

public class ErrorCommand implements by.chmut.catalog.controller.Command {
    @Override
    public String[] execute(String request) {
        String[] result = new String[1];
        result[0] = "Incorrect command or no data in the command! Try again!";
        return result;
    }
}
