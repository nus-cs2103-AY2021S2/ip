import Exceptions.DukeException;
import Exceptions.IncompleteInputException;
import Exceptions.UnknownCommandException;
import Task.*;
import Utils.Command;

import java.util.Scanner;

import static Utils.Print.printWithIndentation;

public class Duke {
    private static final String BOT_NAME = "Chip the Squirrel";
    private static final TaskList taskList = new TaskList();

    public static void processInput(String input) throws DukeException {
        String[] tokens = input.split(" ", 2);
        String command = tokens[0];

        switch(command) {
            case "bye":
                printWithIndentation("Bye! Hope to see you again soon!");
                System.exit(0);
                break;
            case "done":
                taskList.markAsDone(Integer.parseInt(tokens[1]) - 1);
                break;
            case "delete":
                taskList.delete(Integer.parseInt(tokens[1]) - 1);
                break;
            case "list":
                taskList.printTasks();
                break;
            case "todo":
                if (tokens.length == 1) {
                    throw new IncompleteInputException(Command.TODO);
                }

                taskList.addTask(Command.TODO, tokens[1]);
                break;
            case "event":
                if (tokens.length == 1) {
                    throw new IncompleteInputException(Command.EVENT);
                }

                taskList.addTask(Command.EVENT, tokens[1]);
                break;
            case "deadline":
                if (tokens.length == 1) {
                    throw new IncompleteInputException(Command.DEADLINE);
                }

                taskList.addTask(Command.DEADLINE, tokens[1]);
                break;
            default:
                throw new UnknownCommandException(command);
        }
    }

    public static void main(String[] args) {
        printWithIndentation("Hello! I'm " + BOT_NAME + "!", "What can I do for you today?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine().trim();

            if (input.equals("")) {
                continue;
            }

            try {
                processInput(input);
            } catch (DukeException e) {
                printWithIndentation(e.getMessage());
            }
        }
    }
}
