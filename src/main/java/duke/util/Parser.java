package duke.util;

import java.util.ArrayList;
import java.util.Locale;

import duke.Apollo;
import duke.enums.Commands;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidOptionException;
import duke.exceptions.UnrecognisedCommandException;
import duke.tasks.Task;
import duke.tasks.TaskHandler;
import duke.ui.Ui;

/**
 * Represents a parser that handles and makes sense of user input.
 */
public class Parser {

    /**
     * Handles and makes sense of input provided by the user.
     * @param s User's input to the bot.
     * @param taskList TaskList that contains the Tasks.
     * @param apollo Instance of the bot.
     * @throws DukeException Occurs when a command is unrecognised or options are invalid.
     */
    public static void handleInput(String s, ArrayList<Task> taskList, Apollo apollo) throws DukeException {
        String input = s.trim();
        Commands command;

        if (input.equals("")) {
            return;
        }

        String[] inputArr = input.split(" ", 2);

        try {
            command = Commands.valueOf(inputArr[0].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new UnrecognisedCommandException(inputArr[0].toUpperCase(Locale.ROOT));
        }

        switch (command) {
        case LIST:
            TaskHandler.listTasks(taskList);
            break;
        case DONE:
            try {
                TaskHandler.doneTask(inputArr[1], taskList);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new InvalidOptionException(command.name());
            }
        case DELETE:
            try {
                TaskHandler.deleteTask(inputArr[1], taskList);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new InvalidOptionException(command.name());
            }
        case BYE:
            apollo.saveData();
            Ui.displayGoodbyeText();
            System.exit(0);
            break;
        case TODO:
            try {
                TaskHandler.addTask(command, inputArr[1], taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidOptionException(command.name());
            }
            break;
        case DEADLINE:
            try {
                TaskHandler.addTask(command, inputArr[1], taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidOptionException(command.name());
            }
            break;
        case EVENT:
            try {
                TaskHandler.addTask(command, inputArr[1], taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidOptionException(command.name());
            }
            break;
        case FIND:
            try {
                TaskHandler.findTasks(inputArr[1], taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidOptionException(command.name());
            }
            break;
        default:
            Ui.showErrorMessage("Invalid command, please try again!");
        }
    }
}
