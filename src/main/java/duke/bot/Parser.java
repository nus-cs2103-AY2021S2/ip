package duke.bot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;

/** An utility class that translate user inputs into commands executable by the chat bot */
public class Parser {
    /**
     * Parses a command string into an executable command
     *
     * @param text A command string
     * @return An executable Command
     * @throws DukeException if the command string cannot be parsed
     */
    public static Command parse(String text) throws DukeException {
        if (text.matches("^todo($|.+$)")) {
            return parseToDo(text);

        } else if (text.matches("^deadline($|.+$)")) {
            return parseDeadline(text);

        } else if (text.matches("^event($|.+$)")) {
            return parseEvent(text);

        } else if (text.matches("^done($|.+$)")) {
            return parseDone(text);

        } else if (text.matches("^delete($|.+$)")) {
            return parseDelete(text);

        } else if (text.matches("^find($|.+$)")) {
            return parseFind(text);

        } else if (text.equals("list")) {
            return parseList(text);

        } else if (text.equals("bye")) {
            return parseExit(text);

        } else {
            throw new DukeException("No such command, please try again with another command.");
        }
    }

    /**
     * Parses a command string into a ToDo command
     *
     * @param text A command string
     * @return A ToDo command
     * @throws DukeCommandException if the command string contains an empty description
     */
    public static ToDoCommand parseToDo(String text) throws DukeCommandException {
        String desc = text.substring(4).stripLeading();
        if (desc.length() == 0) {
            throw new DukeCommandException("todo", desc, "Description of ToDo cannot be empty");
        } else {
            return new ToDoCommand(desc);
        }
    }

    /**
     * Parses a command string into a Deadline command
     *
     * @param text A command string
     * @return A Deadline command
     * @throws DukeCommandException if the command string has insufficient parameters or invalid date time format
     */
    public static DeadlineCommand parseDeadline(String text) throws DukeCommandException {
        String validDateTimePattern = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-"
                + "([1-9][0-9][0-9][0-9]) ([1-9]|1[0-2])(AM|PM)";

        String params = text.substring(8).stripLeading();

        if (params.length() == 0) {
            throw new DukeCommandException("deadline", params, "The details of a Deadline cannot be empty.");

        } else if (!params.contains("/by") || params.split(" /by ").length != 2) {
            throw new DukeCommandException("deadline", params, "Description and date/time must be given for a "
                    + "Deadline.");

        } else if (!params.split(" /by ")[1].matches(validDateTimePattern)) {
            throw new DukeCommandException("deadline", params, "Date time format is incorrect, try to follow the "
                    + "format of dd-mm-yyyy hAM/PM.");

        } else {
            String[] splits = params.split(" /by ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
            LocalDateTime dateTime = LocalDateTime.parse(splits[1], formatter);

            return new DeadlineCommand(splits[0], dateTime);
        }
    }

    /**
     * Parses a command string into an Event command
     *
     * @param text A command string
     * @return An Event command
     * @throws DukeCommandException if the command string has insufficient parameters or invalid date time format
     */
    public static EventCommand parseEvent(String text) throws DukeCommandException {
        // Regex pattern matching date-month-year xAM/PM
        String pattern = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-"
                + "([1-9][0-9][0-9][0-9]) ([1-9]|1[0-2])(AM|PM)";

        String params = text.substring(5).stripLeading();

        if (params.length() == 0) {
            throw new DukeCommandException("event", params, "The details of a Event cannot be empty.");

        } else if (!params.contains("/start") || !params.contains("/end")
                || params.split(" /start | /end ").length != 3) {
            throw new DukeCommandException("event", params, "Description, start datetime, and end datetime "
                    + "must be given for an Event.");

        } else if (!params.split(" /start | /end ")[1].matches(pattern)
                || !params.split(" /start | /end ")[2].matches(pattern)) {
            throw new DukeCommandException("deadline", params, "Start or end date has incorrect format, try to "
                    + "follow the format of dd-mm-yyyy hAM/PM.");

        } else {
            String[] splits = params.split(" /start | /end ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
            LocalDateTime start = LocalDateTime.parse(splits[1], formatter);
            LocalDateTime end = LocalDateTime.parse(splits[2], formatter);

            return new EventCommand(splits[0], start, end);
        }
    }

    /**
     * Parses a command string into a Done command
     *
     * @param text A command string
     * @return A Done command
     * @throws DukeCommandException if the index in the command string is invalid
     */
    public static DoneCommand parseDone(String text) throws DukeCommandException {
        String params = text.substring(4).stripLeading();

        if (!params.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeCommandException("done", params, "Please provide an actual number for the task you are done"
                    + " with.");
        } else {
            return new DoneCommand(Integer.parseInt(params) - 1);
        }
    }

    /**
     * Parses a command string into a Delete command
     *
     * @param text A command string
     * @return A Delete command
     * @throws DukeCommandException if the index in the command string is invalid
     */
    public static DeleteCommand parseDelete(String text) throws DukeCommandException {
        String params = text.substring(6).stripLeading();

        if (!params.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeCommandException("delete", params, "Please provide an actual number for the task you are "
                    + "deleting.");
        } else {
            return new DeleteCommand(Integer.parseInt(params) - 1);
        }
    }

    /**
     * Parses a command string into a Find command
     *
     * @param text A command String
     * @return A Find command
     * @throws DukeCommandException if the parameters are empty or in invalid format
     */
    public static FindCommand parseFind(String text) throws DukeCommandException {
        String validDatePattern = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-"
                + "([1-9][0-9][0-9][0-9])";

        String params = text.substring(4).stripLeading();

        if (params.length() == 0) {
            throw new DukeCommandException("find", params, "Find command cannot have empty parameter.");

        } else if (params.startsWith("/on") && params.substring(3).trim().length() == 0) {
            throw new DukeCommandException("find", params, "Cannot find by an empty date.");

        } else if (params.startsWith("/on") && !params.substring(3).trim().matches(validDatePattern)) {
            throw new DukeCommandException("find", params, "Date has invalid format, try to "
                    + "follow the format of dd-mm-yyyy.");

        } else if (params.contains(" /on ") && params.split(" /on ").length != 2) {
            throw new DukeCommandException("find", params, "Either the query or the search date is missing.");

        } else if (params.contains(" /on ") && params.split(" /on ").length == 2
                && !params.split(" /on ")[1].matches(validDatePattern)) {
            throw new DukeCommandException("find", params, "Date has invalid format, try to "
                    + "follow the format of dd-mm-yyyy.");

        } else if (params.startsWith("/on")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate targetDate = LocalDate.parse(params.substring(4), formatter);
            return new FindCommand("", targetDate);

        } else if (params.contains(" /on ")) {
            String[] splits = params.split(" /on ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate targetDate = LocalDate.parse(splits[1], formatter);
            return new FindCommand(splits[0], targetDate);

        } else {
            return new FindCommand(params);

        }
    }

    /**
     * Parses a command string into a List command
     *
     * @param text A command string
     * @return A List command
     */
    public static ListCommand parseList(String text) {
        return new ListCommand();
    }

    /**
     * Parses a command string into an Exit command
     *
     * @param text A command string
     * @return An Exit command
     */
    public static ExitCommand parseExit(String text) {
        return new ExitCommand();
    }
}
