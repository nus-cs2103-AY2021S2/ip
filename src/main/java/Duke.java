import Task.*;
import Utils.Command;

import java.util.Scanner;

import static Utils.Print.printWithIndentation;

public class Duke {
    private static final String BOT_NAME = "Chip the Squirrel";
    private static final TaskList taskList = new TaskList();


    public static void processInput(String input) {
        String[] tokens = input.split(" ", 2);
        String command = tokens[0];

        switch(command) {
            case "bye":
                printWithIndentation("Bye! Hope to see you again soon!");
                System.exit(0);
                break;
            case "todo":
                taskList.addTask(Command.TODO, tokens[1]);
                break;
            case "event":
                taskList.addTask(Command.EVENT, tokens[1]);
                break;
            case "deadline":
                taskList.addTask(Command.DEADLINE, tokens[1]);
                break;
            case "done":
                int idx = Integer.parseInt(tokens[1]) - 1;
                taskList.markAsDone(idx);
                break;
            case "list":
                taskList.printTasks();
                break;
            default:
                printWithIndentation("I do not understand.");
        }
    }

    public static void main(String[] args) {
        printWithIndentation("Hello! I'm " + BOT_NAME + "!", "What can I do for you today?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine().trim();
            processInput(input);
        }
    }
}
