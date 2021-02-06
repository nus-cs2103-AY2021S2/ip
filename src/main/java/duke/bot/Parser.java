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
    private static final String SIGNATURE_TODO = "^todo($|.+$)";
    private static final String SIGNATURE_DEADLINE = "^deadline($|.+$)";
    private static final String SIGNATURE_EVENT = "^event($|.+$)";
    private static final String SIGNATURE_DONE = "^done($|.+$)";
    private static final String SIGNATURE_DELETE = "^delete($|.+$)";
    private static final String SIGNATURE_FIND = "^find($|.+$)";
    private static final String SIGNATURE_LIST = "list";
    private static final String SIGNATURE_EXIT = "bye";

    private static final String REGEX_DIGITS = "-?(0|[1-9]\\d*)";
    private static final String REGEX_DATE = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-"
                + "([1-9][0-9][0-9][0-9])";
    private static final String REGEX_DATETIME = "^(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-"
                + "([1-9][0-9][0-9][0-9]) ([1-9]|1[0-2])(AM|PM)";

    /**
     * Parses a command string into an executable command
     *
     * @param text A command string
     * @return An executable Command
     * @throws DukeException if the command string cannot be parsed
     */
    public static Command parse(String text) throws DukeException {
        if (text.matches(SIGNATURE_TODO)) {
            return parseToDo(text);
        } else if (text.matches(SIGNATURE_DEADLINE)) {
            return parseDeadline(text);
        } else if (text.matches(SIGNATURE_EVENT)) {
            return parseEvent(text);
        } else if (text.matches(SIGNATURE_DONE)) {
            return parseDone(text);
        } else if (text.matches(SIGNATURE_DELETE)) {
            return parseDelete(text);
        } else if (text.matches(SIGNATURE_FIND)) {
            return parseFind(text);
        } else if (text.equals(SIGNATURE_LIST)) {
            return parseList(text);
        } else if (text.equals(SIGNATURE_EXIT)) {
            return parseExit(text);
        } else {
            throw new DukeException("No such command, please try again with another command.");
        }
    }

    private static void throwIfToDoInvalid(String params) throws DukeCommandException {
        if (params.length() == 0) {
            throw new DukeCommandException("todo", params, "Description of ToDo cannot be empty");
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

        throwIfToDoInvalid(desc);

        return new ToDoCommand(desc);
    }

    private static void throwIfDeadlineInvalid(String params) throws DukeCommandException {
        boolean hasNoDetail = params.length() == 0;
        boolean hasMissingParams = !params.contains("/by") || params.split(" /by ").length != 2;
        boolean hasIncorrectFormat = params.split(" /by ").length == 2
                && !params.split(" /by ")[1].matches(REGEX_DATETIME);

        if (hasNoDetail) {
            throw new DukeCommandException("deadline", params, "The details of a Deadline cannot be empty.");
        } else if (hasMissingParams) {
            throw new DukeCommandException("deadline", params, "Description and date/time must be given for a "
                    + "Deadline.");
        } else if (hasIncorrectFormat) {
            throw new DukeCommandException("deadline", params, "Date time format is incorrect, try to follow the "
                    + "format of dd-mm-yyyy hAM/PM.");
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
        String params = text.substring(8).stripLeading();

        throwIfDeadlineInvalid(params);

        String[] splits = params.split(" /by ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
        LocalDateTime dateTime = LocalDateTime.parse(splits[1], formatter);
        return new DeadlineCommand(splits[0], dateTime);
    }

    private static void throwIfEventInvalid(String params) throws DukeCommandException {
        boolean hasNoDetails = params.length() == 0;
        boolean hasMissingParams = !params.contains("/start")
                || !params.contains("/end")
                || params.split(" /start | /end ").length != 3;
        boolean hasIncorrectFormat = params.split(" /start | /end ").length != 3
                || !params.split(" /start | /end ")[1].matches(REGEX_DATETIME)
                || !params.split(" /start | /end ")[2].matches(REGEX_DATETIME);

        if (hasNoDetails) {
            throw new DukeCommandException("event", params, "The details of a Event cannot be empty.");
        } else if (hasMissingParams) {
            throw new DukeCommandException("event", params, "Description, start datetime, and end datetime "
                    + "must be given for an Event.");
        } else if (hasIncorrectFormat) {
            throw new DukeCommandException("deadline", params, "Start or end date has incorrect format, try to "
                    + "follow the format of dd-mm-yyyy hAM/PM.");
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
        String params = text.substring(5).stripLeading();

        throwIfEventInvalid(params);

        String[] splits = params.split(" /start | /end ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ha");
        LocalDateTime startDateTime = LocalDateTime.parse(splits[1], formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(splits[2], formatter);
        return new EventCommand(splits[0], startDateTime, endDateTime);
    }

    private static void throwIfDoneInvalid(String params) throws DukeCommandException {
        if (!params.matches(REGEX_DIGITS)) {
            throw new DukeCommandException("done", params, "Please provide an actual number for the task you are done"
                    + " with.");
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

        throwIfDoneInvalid(params);

        return new DoneCommand(Integer.parseInt(params) - 1);
    }

    private static void throwIfDeleteInvalid(String params) throws DukeCommandException {
        if (!params.matches(REGEX_DIGITS)) {
            throw new DukeCommandException("delete", params, "Please provide an actual number for the task you are "
                    + "deleting.");
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

        throwIfDeleteInvalid(params);

        return new DeleteCommand(Integer.parseInt(params) - 1);
    }

    private static void throwIfFindInvalid(String params) throws DukeCommandException {
        boolean hasKeyword = params.contains(" /on ");
        boolean hasNoKeyword = params.startsWith("/on");
        boolean hasNoParam = params.length() == 0;
        boolean hasMissingParam = hasKeyword && params.split(" /on ").length != 2;
        boolean isLackingDate = hasNoKeyword
                && params.length() > 3
                && params.substring(3).trim().length() == 0;
        boolean hasInvalidDateWithoutKeyword = hasNoKeyword
                && params.length() > 3
                && !params.substring(3).trim().matches(REGEX_DATE);
        boolean hasInvalidDateWithKeyword = hasKeyword
                && params.split(" /on ").length == 2
                && !params.split(" /on ")[1].matches(REGEX_DATE);

        if (hasNoParam) {
            throw new DukeCommandException("find", params, "Find command cannot have empty parameter.");
        } else if (isLackingDate) {
            throw new DukeCommandException("find", params, "Cannot find by an empty date.");
        } else if (hasInvalidDateWithoutKeyword || hasInvalidDateWithKeyword) {
            throw new DukeCommandException("find", params, "Date has invalid format, try to "
                    + "follow the format of dd-mm-yyyy.");
        } else if (hasMissingParam) {
            throw new DukeCommandException("find", params, "Either the query or the search date is missing.");
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
        String params = text.substring(4).stripLeading();

        throwIfFindInvalid(params);

        boolean hasKeyword = params.contains(" /on ");
        boolean hasNoKeyword = params.startsWith("/on");

        if (hasNoKeyword) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate targetDate = LocalDate.parse(params.substring(4), formatter);
            return new FindCommand("", targetDate);
        } else if (hasKeyword) {
            String[] splits = params.split(" /on ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate targetDate = LocalDate.parse(splits[1], formatter);
            return new FindCommand(splits[0], targetDate);
        }

        return new FindCommand(params);
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
