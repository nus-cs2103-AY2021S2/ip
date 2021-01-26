import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import customClass.Command;
import customClass.Task;
import customClass.LogicHandler;
import customClass.Load;
import customClass.Save;


public class Duke {
    public static void main(String[] args) {
        String logo = "   ___     ___    _  _     ___     ___     _\n" +
                "  |   \\   /   \\  | \\| |   |_ _|   | __|   | |\n" +
                "  | |) |  | - |  | .` |    | |    | _|    | |__\n" +
                "  |___/   |_|_|  |_|\\_|   |___|   |___|   |____|\n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n";
        System.out.println("Welcome back Max, I'm a Dumb Assistant " +
                "Naively Intended (to) Ease Labour\n" + logo +
                "____________________________________________________________\n" +
                "What can I do for you?\n" +
                "____________________________________________________________");

        //++++++++++++++++++++++++++ IGNORE THE CODE ABOVE +++++++++++++++++++++++++++++

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Task> list = new ArrayList<>();
        Save dataSaver = new Save();
        LogicHandler logic = new LogicHandler();
        Load dataLoader = new Load();
        Command currentCommand;
        try {
            currentCommand = Command.valueOf(input.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            currentCommand = Command.ERROR;
        }

        try {
            dataLoader.loadData(list);
        } catch (IOException e) {
            // Do not output error message, instead, create the dir and file
            File dir = new File("src/data");
            dir.mkdir();
            File file = new File("src/data/tasks.txt");
            try {
                file.createNewFile();
            } catch (IOException err) {
                System.out.println("Error: " + err);
            }
        }

        while (currentCommand != Command.BYE) {
            System.out.println("____________________________________________________________");
            switch (currentCommand) {
            case LIST:
                logic.list(list);
                break;
            case DONE:
                logic.done(input, list);
                break;
            case TODO:
                logic.todo(input, list);
                break;
            case DEADLINE:
                logic.deadline(input, list);
                break;
            case EVENT:
                logic.event(input, list);
                break;
            case DELETE:
                logic.delete(input, list);
                break;
            case ERROR:
                System.out.println("Oops, that is not a command I support.");
                break;
            default:
                System.out.println("Internal error in code.");
            }

            System.out.println("____________________________________________________________");
            input = sc.nextLine();
            try {
                currentCommand = Command.valueOf(input.split(" ")[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                currentCommand = Command.ERROR;
            }
        }
        dataSaver.save(list);

        System.out.println("Bye. Hope to see you again soon!");
    }
}


