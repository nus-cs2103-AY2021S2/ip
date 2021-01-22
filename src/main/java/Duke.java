import controller.Parser;
import controller.Storage;
import controller.TaskList;
import controller.Ui;

import java.util.Scanner;

public class Duke {
    private static final String FILENAME = "duke.csv";

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Ui ui = new Ui();
        ui.initialize();
        Storage storage = new Storage(FILENAME);
        TaskList listOfTasks = storage.handleLoad();
        Parser parser = new Parser(ui);

        Scanner sc = new Scanner(System.in);
        System.out.print(">>> ");
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                parser.handleList(listOfTasks);
            } else {
                // handle the commands with arguments
                int spaceIndex = input.indexOf(" ");
                int cutOffPoint = spaceIndex == -1 ? input.length() : spaceIndex;
                String command = input.substring(0, cutOffPoint);

                switch (command) {
                    case "done":
                        parser.handleDone(input, listOfTasks);
                        break;
                    case "todo":
                        parser.handleTodo(input, listOfTasks);
                        break;
                    case "delete":
                        parser.handleDelete(input, listOfTasks);
                        break;
                    case "deadline":
                    case "event":
                        parser.handleTasksWithTime(command, input, listOfTasks);
                        break;
                    default:
                        System.out.println("I have no idea what that means, what do you want?");
                        break;
                }
            }
            storage.handleSave(listOfTasks);

            System.out.print(">>> ");
            input = sc.nextLine();
        }
        ui.exit();
    }
}
