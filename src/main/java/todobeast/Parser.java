package todobeast;

import todobeast.commands.*;
import todobeast.exceptions.InvalidCommandException;
import todobeast.exceptions.InvalidInputException;
import todobeast.exceptions.ToDoBeastException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {

    public static Command parse(String fullCommand) throws ToDoBeastException {

        Command command = null;
        String[] dateAndTimeTokens = null;
        // full command will come delimited by ", "
        fullCommand = fullCommand.trim();
        String[] commandArgs = fullCommand.split(",");
        // removing leading and trailing whitespaces
        commandArgs = Arrays.stream(commandArgs).map(String::trim).toArray(String[]::new);

        // command will reside in index 0
        switch (commandArgs[0].toLowerCase()) {
        case "bye":
        case "exit":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        // format e.g.: done, 1
        case "done":
            checkTaskIndex(commandArgs);
            int doneIndex = Integer.parseInt(commandArgs[1]);
            command = new DoneCommand(doneIndex);
            break;
        // format e.g.: delete, 1
        case "delete":
            checkTaskIndex(commandArgs);
            int deleteIndex = Integer.parseInt(commandArgs[1]);
            command = new DeleteCommand(deleteIndex);
            break;
        case "find":
            command = new FindCommand(commandArgs[1]);
            break;
        // format e.g.: todo, thing
        case "todo":
            TaskType todoType = TaskType.TODO;
            checkTaskArgsLength(commandArgs, todoType);
            command = new AddCommand(todoType, commandArgs[1]);
            break;
        // format e.g.: deadline, thing, by YYYY-MM-DD HH:MM
        case "deadline":
            TaskType deadlineType = TaskType.DEADLINE;
            checkTaskArgsLength(commandArgs, deadlineType);
            dateAndTimeTokens = splitDateAndTime(commandArgs[2], deadlineType);
            checkValidDateAndTimeFormat(dateAndTimeTokens[0], dateAndTimeTokens[1]);
            command = new AddCommand(deadlineType, commandArgs[1], LocalDate.parse(dateAndTimeTokens[0]), LocalTime.parse(dateAndTimeTokens[1]));
            break;
        // format e.g.: event, thing, at YYYY-MM-DD HH:MM
        case "event":
            TaskType eventType = TaskType.EVENT;
            checkTaskArgsLength(commandArgs, eventType);
            dateAndTimeTokens = splitDateAndTime(commandArgs[2], eventType);
            checkValidDateAndTimeFormat(dateAndTimeTokens[0], dateAndTimeTokens[1]);
            command = new AddCommand(eventType, commandArgs[1], LocalDate.parse(dateAndTimeTokens[0]), LocalTime.parse(dateAndTimeTokens[1]));
            break;
        default:
            throw new InvalidCommandException("Command provided is invalid.");
        }

        return command;
    }

    public static void checkTaskIndex(String[] commandArgs) throws InvalidInputException {
        if (commandArgs.length != 2) {
            throw new InvalidInputException("Invalid number of arguments provided.");
        } else {
            try {
                int index = Integer.parseInt(commandArgs[1]);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Index provided is not a number.");
            }
        }
    }

    public static String[] splitDateAndTime(String dateAndTime, TaskType taskType) throws InvalidInputException {
        String[] tokens = dateAndTime.split(" ");
        switch (tokens[0]) {
        case "by":
            if (taskType != TaskType.DEADLINE) {
                throw new InvalidInputException("\"by\" keyword only can be used for deadlines.");
            }
            break;
        case "at":
            if (taskType != TaskType.EVENT) {
                throw new InvalidInputException("\"at\" keyword only can be used for events.");
            }
            break;
        default:
            throw new InvalidInputException("No date and time keyword specifier.");
        }

        return new String[]{tokens[1], tokens[2]};
    }

    public static void checkValidDateAndTimeFormat(String date, String time) throws InvalidInputException {
        try {
            LocalDate ld = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Date provided is in wrong format; should be YYYY-MM-DD.");
        }

        try {
            LocalTime lt = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Time provided is in wrong format; should be HH:MM.");
        }
    }

    public static void checkTaskArgsLength(String[] commandArgs, TaskType taskType) throws InvalidInputException {
        boolean hasInvalidArgsLength = false;
        switch (taskType) {
        case TODO:
            if (commandArgs.length != 2) {
                hasInvalidArgsLength = true;
            }
            break;
        case DEADLINE:
        case EVENT:
            if (commandArgs.length != 3) {
                hasInvalidArgsLength = true;
            }
            break;
        }

        if (hasInvalidArgsLength) {
            throw new InvalidInputException("Command arguments have invalid length.");
        }
    }
}
