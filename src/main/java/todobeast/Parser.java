package todobeast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import todobeast.commands.AddCommand;
import todobeast.commands.AddNotesCommand;
import todobeast.commands.Command;
import todobeast.commands.DeleteCommand;
import todobeast.commands.DoneCommand;
import todobeast.commands.ExitCommand;
import todobeast.commands.FindCommand;
import todobeast.commands.ListCommand;
import todobeast.commands.ShowInstructionsCommand;
import todobeast.commands.TaskType;
import todobeast.exceptions.InvalidCommandException;
import todobeast.exceptions.InvalidInputException;
import todobeast.exceptions.ToDoBeastException;

/**
 * A utility class that parses commands given by the user, and generates the appropriate command for the application
 * to execute.
 *
 * If the command given is invalid or in an invalid format, the Parser will throw an exception. Additional helper
 * methods are provided in the class to check for such invalid inputs.
 */
public class Parser {

    /**
     * Provides the main logic to parse user input commands. Commands are given in the following format, and each
     * parameter is separated by a comma (,):
     * <code>command, taskDescription, at/by date in YYYY-MM-DD format (if applicable) and time in HH:MM (24-hr) format
     * (if
     * applicable) </code>
     *
     * @param fullCommand the full command input given by the user
     * @return a Command corresponding to the user input given
     * @throws ToDoBeastException if the command given by the user is invalid, or in an invalid format.
     * @see Command
     */
    public static Command parse(String fullCommand) throws ToDoBeastException {

        assert fullCommand.length() > 0 : "Empty command given!";

        Command command = null;
        String[] dateAndTimeTokens = null;
        // full command will come delimited by ", "
        fullCommand = fullCommand.trim();
        String[] commandArgs = fullCommand.split(",");
        // removing leading and trailing whitespaces
        commandArgs = Arrays.stream(commandArgs).map(String::trim).toArray(String[]::new);

        // command will reside in index 0
        switch (commandArgs[0].toLowerCase()) {
        case "instructions":
        case "instruction":
            command = new ShowInstructionsCommand();
            break;
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
            assert doneIndex >= 0 : "Index provided is a negative number";
            command = new DoneCommand(doneIndex);
            break;
        // format e.g.: delete, 1
        case "delete":
            checkTaskIndex(commandArgs);
            int deleteIndex = Integer.parseInt(commandArgs[1]);
            assert deleteIndex >= 0 : "Index provided is a negative number";
            command = new DeleteCommand(deleteIndex);
            break;
        case "find":
            command = new FindCommand(commandArgs[1]);
            break;
        // format e.g.: notes, 1, taskNotes
        case "note":
        case "notes":
            checkTaskIndex(commandArgs);
            int taskIndexToAddNotes = Integer.parseInt(commandArgs[1]);
            assert taskIndexToAddNotes >= 0 : "Index provided is a negative number";
            command = new AddNotesCommand(taskIndexToAddNotes, commandArgs[2]);
            break;
        // format e.g.: todo, thing, (optional) notes
        case "todo":
            TaskType todoType = TaskType.TODO;
            checkTaskArgsLength(commandArgs, todoType);
            if (commandArgs.length == 2) {
                command = new AddCommand(todoType, commandArgs[1], null, null, null);
            } else {
                command = new AddCommand(todoType, commandArgs[1], null, null, commandArgs[2]);
            }
            break;
        // format e.g.: deadline, thing, by YYYY-MM-DD HH:MM, (optional) notes
        case "deadline":
            TaskType deadlineType = TaskType.DEADLINE;
            checkTaskArgsLength(commandArgs, deadlineType);
            dateAndTimeTokens = splitDateAndTime(commandArgs[2], deadlineType);
            checkValidDateAndTimeFormat(dateAndTimeTokens[0], dateAndTimeTokens[1]);
            command = getTimeBasedCommand(dateAndTimeTokens, commandArgs, deadlineType);
            break;
        // format e.g.: event, thing, at YYYY-MM-DD HH:MM, (optional) notes
        case "event":
            TaskType eventType = TaskType.EVENT;
            checkTaskArgsLength(commandArgs, eventType);
            dateAndTimeTokens = splitDateAndTime(commandArgs[2], eventType);
            checkValidDateAndTimeFormat(dateAndTimeTokens[0], dateAndTimeTokens[1]);
            command = getTimeBasedCommand(dateAndTimeTokens, commandArgs, eventType);
            break;
        default:
            throw new InvalidCommandException("Command provided is invalid.");
        }

        return command;
    }

    private static Command getTimeBasedCommand(String[] dateAndTimeTokens,
                                               String[] commandArgs, TaskType deadlineType) {
        Command command;
        if (commandArgs.length == 3) {
            command = new AddCommand(deadlineType, commandArgs[1], LocalDate.parse(dateAndTimeTokens[0]),
                    LocalTime.parse(dateAndTimeTokens[1]), null);
        } else {
            command = new AddCommand(deadlineType, commandArgs[1], LocalDate.parse(dateAndTimeTokens[0]),
                    LocalTime.parse(dateAndTimeTokens[1]), commandArgs[3]);
        }
        return command;
    }

    /**
     * Checks validity of delete and done commands. They should be given in the following format:
     * <code>delete/done, int task index</code>
     * @param commandArgs the command parameters/arguments parsed from user input
     * @throws InvalidInputException if the number of parameters provided is incorrect, or the index provided is not
     * an integer
     */
    public static void checkTaskIndex(String[] commandArgs) throws InvalidInputException {
        if (commandArgs.length < 2 || commandArgs.length > 3) {
            throw new InvalidInputException("Invalid number of arguments provided.");
        } else {
            try {
                int index = Integer.parseInt(commandArgs[1]);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Index provided is not a number.");
            }
        }
    }

    /**
     * Splits a given string with the appropriate date and time format into a String array. Tasks of "deadline" type
     * must have a "by" prefix attached, and tasks of "event" type must have a "at" prefix attached.
     *
     * @param dateAndTime string containing the date and time in appropriate format
     * @param taskType enumeration that determines the type of task that the date and time is being checked for
     * @return a String array containing the date at index 0 and the time at index 1
     * @throws InvalidInputException if "by" or "at" keyword is not specified
     */
    private static String[] splitDateAndTime(String dateAndTime, TaskType taskType) throws InvalidInputException {
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

    /**
     * Checks if given strings are in the appropriate format specified. YYYY-MM-DD for date, and HH:MM for time.
     *
     * @param date the date to be checked
     * @param time the time to be checked
     * @throws InvalidInputException if wrong format is provided
     */
    private static void checkValidDateAndTimeFormat(String date, String time) throws InvalidInputException {
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

    /**
     * Checks if the user-input command was split properly according to the specified delimiter
     * @param commandArgs array containing user inputs that have been split according to the delimiter
     * @param taskType enumeration that determines the type of task that is being checked for
     * @throws InvalidInputException if the length of the user input array differs from what is expected
     */
    private static void checkTaskArgsLength(String[] commandArgs, TaskType taskType) throws InvalidInputException {
        boolean hasInvalidArgsLength = false;
        switch (taskType) {
        case TODO:
            if (commandArgs.length < 2 || commandArgs.length > 3) {
                hasInvalidArgsLength = true;
            }
            break;
        case DEADLINE:
        case EVENT:
            if (commandArgs.length < 3 || commandArgs.length > 4) {
                hasInvalidArgsLength = true;
            }
            break;
        default:
            throw new InvalidInputException("Invalid task type specified!");
        }

        if (hasInvalidArgsLength) {
            throw new InvalidInputException("Command arguments have invalid length.");
        }
    }
}
