package duke;

import java.util.Scanner;

import static duke.Display.displayAllTasks;
import static duke.Display.displayError;
import static duke.Duke.*;
import static duke.data.Data.updateDataFile;

public class Parser {

    private static CommandType getCommandType(String command) throws DukeException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (Exception e) {
//            return CommandType.INVALID;
            throw new DukeException("That doesn't seem to be an item on our menu...");
        }
    }

    public static void handleInput(Scanner sc) {
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.equals("bye")) {
                break;
            }
            String[] command = input.split(" ", 2);
            try {
                CommandType type = getCommandType(command[0]);
                switch (type) {
                case LIST:
                    displayAllTasks();
                    break;
                case DONE:
                    markDone(command);
                    break;
                case TODO:
                    // Fallthrough
                case EVENT:
                    // Fallthrough
                case DEADLINE:
                    addTask(type, command);
                    break;
                case DELETE:
                    deleteTask(command);
                    break;
                }
                updateDataFile(tasks);
            } catch (DukeException e) {
                displayError(e.getMessage());
            }
        }
    }
}
