package by.chmut.catalog;

import by.chmut.catalog.controller.Controller;
import by.chmut.catalog.view.View;

public class Main {


    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.doAction("read-name=All");
        while (true) {
            printInfoForCommand();
            String request = View.getRequest();
            if (request.equalsIgnoreCase("quit")) {
                break;
            }
            String[] response = controller.doAction(request);
            if (response == null) {
                break;
            } else {
                View.showResult(response);
            }
        }
        controller.doAction("save-name=All");
        System.out.println("Bye - bye!");
    }

    private static void printInfoForCommand() {
        System.out.println("\u001B[34mCommands : " );
        System.out.println("search -name = [some name of news]  - search on Name " );
        System.out.println("search -date = [some date of news]  - search on Date news " );
        System.out.println("search -body = [some text of news]  - search on context " );
        System.out.println("add -category = [category name] -subcategory = [subcategory name] " +
                "-newsName = [name of news] -news = [context] -date = [date of news] -provider = [author]");
        System.out.println("Caution! on command  'add' if field empty - this save as null.");
        System.out.println("for save and exit - command 'quit' !!! \u001B[0m");
    }
}

//add -category=film -subcategory=action -newsName= Mission Impossible 3 -news= New film on DVD -date=28.12.2006 -provider = Chmut
