package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

/**
 * Handles all parsing of input by user into commands, description and date (if applicable)
 */
public class Parser {

    /**
     * Determines command given by user input
     *
     * @param text Input given by user
     * @return Command to be supplied to be executed
     * @throws DukeException If given an empty description, empty selection, invalid integer, unrecognized command
     */
    public static Command parse(String text) throws DukeException {
        String command = getCommand(text);
        String description = getDescription(text);
        Command commandType;

        handleInputErrors(command, description);

        switch (command) {
        case "find":
            commandType = new FindCommand(command, description);
            break;
        case "list":
            commandType = new ListCommand(command);
            break;
        case "done":
            commandType = new DoneCommand(command, description);
            break;
        case "delete":
            commandType = new DeleteCommand(command, description);
            break;
        case "bye":
            commandType = new ByeCommand(command);
            break;
        case "todo":
            commandType = new AddCommand(command, description);
            break;
        case "event":
        case "deadline":
            String date = getDate(text);
            commandType = new AddCommand(command, description, date);
            break;
        default:
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        return commandType;
    }

    private static String getCommand(String text) {
        String[] commandLine = text.split(" ");
        assert commandLine.length > 0 : "Cannot differentiate command from given input";
        return commandLine[0];
    }

    private static String getDescription(String text) {
        String command = getCommand(text);
        text = text.replaceFirst(command + " ", "");
        String description = "";

        switch (command) {
        case "find":
        case "delete":
        case "done":
        case "todo":
            description = text;
            break;
        case "event":
            description = text.split(" /at")[0].replaceFirst("event ", "");
            break;
        case "deadline":
            description = text.split(" /by")[0].replaceFirst("deadline ", "");
            break;
        default:
            break;
        }

        return description;
    }

    private static String getDate(String text) {
        String command = getCommand(text);
        text = text.replaceFirst(command + " ", "");
        String date = "";

        assert text.contains("/at") || text.contains("/by") : "Date for /at or /by not specified";

        switch (command) {
        case "event":
            date = text.split(" /at ")[1];
            break;
        case "deadline":
            date = text.split(" /by ")[1];
            break;
        default:
            break;
        }

        return date;
    }

    private static void handleInputErrors(String command, String description) throws DukeException {
        // Throws exception for ToDo, find, Event and Deadline commands
        if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
            if (description.equals(command)) {
                //Empty description
                throw new DukeException(command, DukeExceptionType.EMPTY_DESCRIPTION);
            }
        }

        // Throws exception for find command
        if (command.equals("find")) {
            if (description.equals(command)) {
                //Empty keyword
                throw new DukeException(command, DukeExceptionType.EMPTY_KEYWORD);
            }
        }

        // Throws exception for done and delete commands
        if (command.equals("done") || command.equals("delete")) {
            if (description.equals(command)) {
                throw new DukeException(command, DukeExceptionType.EMPTY_SELECTION);
            }
            if (!Utility.isNumeric(description)) {
                // Selection not numeric
                throw new DukeException(command, DukeExceptionType.INVALID_INTEGER);
            }
        }
    }

}
