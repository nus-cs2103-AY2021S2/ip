package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.DukeExceptionType;

public class Parser {

    public static Command parse(String text) throws DukeException {
        String command = command(text);
        String description = description(text);
        Command commandType = null;

        // Throws exception for Duke.Tasks.ToDo, Duke.Tasks.Event and Duke.Tasks.Deadline tasks
        if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
            if (description.equals(command)) {
                //Empty description
                throw new DukeException(command, DukeExceptionType.EMPTY_DESCRIPTION);
            }
        }

        // Throws exception for done and delete commands
        if (command.equals("done") || command.equals("delete")) {
            if (description.equals(command)) {
                throw new DukeException(command, DukeExceptionType.EMPTY_SELECTION);
            } else if (!Utility.isNumeric(description)) {
                // Selection not numeric
                throw new DukeException(command, DukeExceptionType.INVALID_INTEGER);
            }
        }

        switch (command) {
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
            String date = date(text);
            commandType = new AddCommand(command, description, date);
            break;
        default:
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        return commandType;
    }

    private static String command(String text) {
        String[] commandLine = text.split(" ");
        return commandLine[0];
    }

    private static String description(String text) {
        String command = command(text);
        text = text.replaceFirst(command + " ", "");
        String description = "";

        switch (command) {
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

    private static String date(String text) {
        String command = command(text);
        text = text.replaceFirst(command + " ", "");
        String date = "";

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

}
