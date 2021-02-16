package duke.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import duke.commands.AddTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidInputCommand;
import duke.commands.ListCommand;
import duke.commands.ReminderCommand;
import duke.exceptions.EmptyInputException;
import duke.exceptions.InvalidActionException;
import duke.exceptions.InvalidDateTimeFormatException;
import duke.exceptions.InvalidUrgencyDaysException;
import duke.exceptions.MissingDeadlineException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingEventTimeException;
import duke.exceptions.TaskNumberInvalidException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;


/**
 * Handles the conversion of raw users' input into logic that runs the application.
 */
public class Parser {
    private static final String BYE = "BYE";
    private static final String LIST = "LIST";
    private static final String DONE = "DONE";
    private static final String DELETE = "DELETE";
    private static final String FIND = "FIND";
    private static final String TODO = "TODO";
    private static final String DEADLINE = "DEADLINE";
    private static final String EVENT = "EVENT";
    private static final String REMINDER = "REMINDER";

    private static final ArrayList<String> validActions =
            new ArrayList<>(Arrays.asList(BYE, LIST, DONE, DELETE, FIND, REMINDER, TODO, DEADLINE, EVENT));

    /**
     * Parses a line of raw user input, converting it into a <code>Command</code> object that
     * handles all of the application's logic. Accordingly, the output <code>Command</code> object
     * will be used to:
     * (1) alter the application's state
     * (2) computes responses to the users
     * (3) determine whether to terminate the application
     *
     * @param input A line of raw user input.
     * @return Returns a <code>Command</code> object which will be used to execute the desired responses.
     */
    public static Command parse(String input) {
        // If the input is valid, getExceptionMessage will return null.
        // If the input is invalid, getExceptionMessage will return an appropriate message to be displayed.
        String exceptionMessage = getExceptionMessage(input);
        if (exceptionMessage != null) {
            return new InvalidInputCommand(exceptionMessage);
        }

        String action = getAction(input);
        String description = getDescription(input);
        LocalDateTime deadlineDateTime = convertToDateTime(getByDateTimeString(input));
        LocalDateTime eventDateTime = convertToDateTime(getAtDateTimeString(input));

        switch (action) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(Integer.parseInt(description));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(description));
        case REMINDER:
            return new ReminderCommand(Integer.parseInt(description));
        case FIND:
            return new FindCommand(description);
        case TODO:
            return new AddTaskCommand(new ToDo(description));
        case DEADLINE:
            return new AddTaskCommand(new Deadline(description, deadlineDateTime));
        case EVENT:
            return new AddTaskCommand(new Event(description, eventDateTime));
        default:
            return new InvalidInputCommand("I do not understand your input :O");
        }
    }

    /**
     * Extracts the action's type from a line of raw user input, which, according to
     * the app's specifications, is simply the first token.
     *
     * @param input A line of raw user input.
     * @return The action type to take.
     */
    private static String getAction(String input) {
        return (input + " ").split(" ")[0].toUpperCase();
    }

    /**
     * Removes the first token, i.e. the action's type, from the input string and returns the remainder.
     *
     * @param input A line of raw user input.
     * @return The input with the action's type substring/token removed.
     */
    private static String getRemainingTokens(String input) {
        if (!input.contains(" ")) {
            return "";
        }
        return input.split(" ", 2)[1];
    }

    /**
     * Extracts the action's description from a line of raw user input.
     *
     * @param input A line of raw user input.
     * @return The description substring/tokens accompanying the action.
     */
    private static String getDescription(String input) {
        String action = getAction(input);
        String remainingTokens = getRemainingTokens(input);

        if (action.equals(DEADLINE) && remainingTokens.contains("/by")) {
            return remainingTokens.split("/by")[0].trim();
        } else if (action.equals(EVENT) && remainingTokens.contains("/at")) {
            return remainingTokens.split("/at")[0].trim();
        } else {
            return remainingTokens;
        }
    }

    /**
     * If the action is to add a <code>Deadline</code> to the to-do list, then extract the
     * deadline from the raw user input.
     *
     * @param input A line of raw user input.
     * @return A substring/token representing the deadline, or an empty string if not applicable.
     */
    private static String getByDateTimeString(String input) {
        String action = getAction(input);
        String remainingTokens = getRemainingTokens(input);
        if (action.equals(DEADLINE) && remainingTokens.contains("/by")) {
            return remainingTokens.split("/by", 2)[1].trim();
        }
        return "";
    }

    /**
     * If the action is to add a <code>Event</code> to the to-do list, then extract the
     * event's time from the raw user input.
     *
     * @param input A line of raw user input.
     * @return A substring/token representing the event's time, or an empty string if not applicable.
     */
    private static String getAtDateTimeString(String input) {
        String action = getAction(input);
        String remainingTokens = getRemainingTokens(input);
        if (action.equals(EVENT) && remainingTokens.contains("/at")) {
            return remainingTokens.split("/at", 2)[1].trim();
        }
        return "";
    }

    /**
     * Determines if a <code>String</code> can be converted to a positive integer.
     *
     * @param str Any string.
     * @return True if the input string can be converted to a positive integer, and false otherwise.
     */
    private static boolean isPositiveInteger(String str) {
        if (str == null) {
            return false;
        }

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return Integer.parseInt(str) > 0;
    }

    /**
     * Converts an input date or datetime string into a <code>LocalDateTime</code> object.
     *
     * @param dateTimeString A date or datetime string of format YYYY-MM-DD or YYYY-MM-DD HH:mm
     *                       respectively.
     * @return A datetime converted from the input date or datetime string, but only if they were
     * in the specified format. Else, null is returned.
     */
    public static LocalDateTime convertToDateTime(String dateTimeString) {
        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            dateTime = null;
        }

        if (null != dateTime) {
            return dateTime;
        }

        try {
            LocalDate date = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("uuuu-MM-dd"));
            return LocalDateTime.of(date, LocalTime.MIDNIGHT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Given a line of user input, obtains the exception message if the input was invalid.
     *
     * @param input A line of raw user input.
     * @return An exception message (if applicable), or an empty string if the input was valid.
     */
    private static String getExceptionMessage(String input) {
        try {
            if (input.length() == 0) {
                throw new EmptyInputException();
            }
        } catch (EmptyInputException e) {
            return e.getMessage();
        }

        String action = getAction(input);
        String description = getDescription(input);
        String byDateTimeString = getByDateTimeString(input);
        String atDateTimeString = getAtDateTimeString(input);

        try {
            if (!validActions.contains(action)) {
                throw new InvalidActionException(action);
            }
            if ((!action.equals(BYE) && !action.equals(LIST)) & description.length() == 0) {
                throw new MissingDescriptionException(action);
            }
            if ((action.equals(DONE) || action.equals(DELETE)) && (!isPositiveInteger(description))) {
                throw new TaskNumberInvalidException();
            }
            if (action.equals(REMINDER) && (!isPositiveInteger(description))) {
                throw new InvalidUrgencyDaysException();
            }
            if (action.equals(DEADLINE) && byDateTimeString.length() == 0) {
                throw new MissingDeadlineException();
            }
            if (action.equals(EVENT) && atDateTimeString.length() == 0) {
                throw new MissingEventTimeException();
            }
            if (action.equals(DEADLINE) && null == convertToDateTime(byDateTimeString)) {
                throw new InvalidDateTimeFormatException(byDateTimeString);
            }
            if (action.equals(EVENT) && null == convertToDateTime(atDateTimeString)) {
                throw new InvalidDateTimeFormatException(atDateTimeString);
            }

        } catch (MissingDescriptionException
                | InvalidActionException
                | TaskNumberInvalidException
                | MissingDeadlineException
                | MissingEventTimeException
                | InvalidDateTimeFormatException
                | InvalidUrgencyDaysException e) {
            return e.getMessage();
        }

        return null;
    }
}
