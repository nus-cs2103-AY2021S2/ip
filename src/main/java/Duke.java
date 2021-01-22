import controller.*;

import java.util.Scanner;

public class Duke {
    private static final String FILENAME = "duke.csv";

    public static void main(String[] args) {
        run();
    }

    /**
     * Runs the application.
     */
    public static void run() {
        Ui ui = new Ui();
        ui.initialize();
        Storage storage = new Storage(FILENAME);
        TaskList listOfTasks = null;
        try {
            listOfTasks = storage.handleLoad();
        } catch (DukeException e) {
            System.out.println(e.toString());
            System.exit(1);
        }
        Parser parser = new Parser(ui);

        Scanner sc = new Scanner(System.in);
        System.out.print(">>> ");
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
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
                    case "find":
                        parser.handleFind(input, listOfTasks);
                        break;
                    default:
                        System.out.println("I have no idea what that means, what do you want?");
                        break;
                    }
                }
                storage.handleSave(listOfTasks);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }

            System.out.print(">>> ");
            input = sc.nextLine();
        }
        ui.exit();
    }
}
