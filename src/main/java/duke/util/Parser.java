package duke.util;

import java.util.ArrayList;
import java.util.Locale;

import duke.Duke;
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
    public static void handleInput(String s, ArrayList<Task> taskList, Duke apollo) throws DukeException {
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
            handleDone(command, inputArr[1], taskList);
            break;
        case DELETE:
            handleDelete(command, inputArr[1], taskList);
            break;
        case BYE:
            handleBye(apollo);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            handleTask(command, inputArr[1], taskList);
            break;
        case FIND:
            handleFind(command, inputArr[1], taskList);
            break;
        default:
            Ui.showErrorMessage("Invalid command, please try again!");
        }
    }

    /**
     * Saves data & then shutdown Apollo.
     * @param apollo Instance of Apollo.
     */
    private static void handleBye(Duke apollo) {
        apollo.saveData();
        Ui.displayGoodbyeText();
        System.exit(0);
    }

    /**
     * Calls TaskHandler for Done command.
     * @param command Command that has been called.
     * @param input Input from user command.
     * @param taskList TaskList containing tasks.
     * @throws InvalidOptionException Occurs when a command has been passed in with the wrong format.
     */
    private static void handleDone(Commands command, String input, ArrayList<Task> taskList) throws InvalidOptionException {
        try {
            TaskHandler.doneTask(input, taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidOptionException(command.name());
        }
    }

    /**
     * Calls TaskHandler for Delete command.
     * @param command Command that has been called.
     * @param input Input from user command.
     * @param taskList TaskList containing tasks.
     * @throws InvalidOptionException Occurs when a command has been passed in with the wrong format.
     */
    private static void handleDelete(Commands command, String input, ArrayList<Task> taskList) throws InvalidOptionException {
        try {
            TaskHandler.deleteTask(input, taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidOptionException(command.name());
        }
    }

    /**
     * Calls TaskHandler for Find command.
     * @param command Command that has been called.
     * @param input Input from user command.
     * @param taskList TaskList containing tasks.
     * @throws InvalidOptionException Occurs when a command has been passed in with the wrong format.
     */
    private static void handleFind(Commands command, String input, ArrayList<Task> taskList) throws InvalidOptionException {
        try {
            TaskHandler.findTasks(input, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidOptionException(command.name());
        }
    }

    /**
     * Calls TaskHandler for Task command.
     * @param command Command that has been called.
     * @param input Input from user command.
     * @param taskList TaskList containing tasks.
     * @throws DukeException Occurs when a command has been passed in with the wrong format.
     */
    private static void handleTask(Commands command, String input, ArrayList<Task> taskList) throws DukeException {
        try {
            TaskHandler.addTask(command, input, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidOptionException(command.name());
        }
    }
}
