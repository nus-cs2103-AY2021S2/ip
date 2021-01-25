import Exceptions.DukeException;
import Exceptions.IncompleteInputException;
import Exceptions.UnknownCommandException;
import Storage.Storage;
import Task.*;
import Utils.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Utils.Print.printWithIndentation;

public class Duke {
    private static final String BOT_NAME = "Chip the Squirrel";
    private static final TaskList taskList = new TaskList();
    private static final Storage storage = new Storage("data/tasks.txt");

    public static void loadData() {
        try {
            ArrayList<Task> tasks = storage.load();
            taskList.setTaskList(tasks);
        } catch (IOException e) {
            printWithIndentation("Sorry something when wrong loading your safe file :(");
            System.exit(0);
        } catch (DukeException e) {
            printWithIndentation(e.getMessage());
            System.exit(0);
        }
    }

    public static void saveData() {
        try {
            storage.save(taskList.getTaskList());
        } catch (DukeException e) {
            printWithIndentation(e.getMessage());
            System.exit(0);
        }
    }

    public static void processInput(String input) throws DukeException {
        String[] tokens = input.split(" ", 2);
        Command command;

        try {
            command = Command.valueOf(tokens[0].trim().toUpperCase());
        } catch (IllegalArgumentException e){
            throw new UnknownCommandException(tokens[0].trim());
        }

        switch(command) {
            case BYE:
                printWithIndentation("Bye! Hope to see you again soon!");
                System.exit(0);
                break;
            case DONE:
                try {
                    taskList.markAsDone(Integer.parseInt(tokens[1]) - 1);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new IncompleteInputException(command);
                }
                break;
            case DELETE:
                try {
                    taskList.delete(Integer.parseInt(tokens[1]) - 1);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new IncompleteInputException(command);
                }
                break;
            case LIST:
                taskList.printTasks();
                break;
            default:
                try {
                    taskList.addTask(command, tokens[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IncompleteInputException(command);
                }
        }

        saveData();
    }

    public static void main(String[] args) {
        loadData();

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
