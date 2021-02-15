package pason.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pason.commands.AddCommand;
import pason.commands.ByeCommand;
import pason.commands.Command;
import pason.commands.DeleteCommand;
import pason.commands.DoneCommand;
import pason.commands.FindCommand;
import pason.commands.ListCommand;
import pason.commands.ListScheduleCommand;
import pason.commands.UnknownCommand;
import pason.exceptions.PasonException;
import pason.tasks.Deadline;
import pason.tasks.Event;
import pason.tasks.ToDo;

/**
 * Parser class for validating commands and inputs.
 */
public class Parser {
    public static final int VALID_DEADLINE_FORMAT_COUNT = 2;
    public static final int VALID_EVENT_FORMAT_COUNT = 2;
    public static final int VALID_SCHEDULE_FORMAT_COUNT = 2;
    public static final int OPTIONAL_TIME_COUNT = 2;
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TODO_REGEX = "(todo) ([\\w ]*)";
    /**
     * Parses input string to command type.
     * Returns the appropriate command to execute.
     *
     * @param input  User input to be parsed.
     * @return Command object or validation method.
     * @throws Exception  If invalid input or formatting.
     */
    public static Command parseCommand(String input) throws Exception {
        String[] splitInput = input.split(" ");
        String command = splitInput[0].toLowerCase();
        switch (command) {
        case "bye":
            return new ByeCommand(input);
        case "list":
            return new ListCommand(input);
        case "todo":
            return Parser.validateToDo(input);
        case "deadline":
            return Parser.validateDeadline(input);
        case "event":
            return Parser.validateEvent(input);
        case "done":
            return new DoneCommand(input, Integer.parseInt(splitInput[1]));
        case "delete":
            return new DeleteCommand(input, Integer.parseInt(splitInput[1]));
        case "find":
            return new FindCommand(input, splitInput[1]);
        case "listschedule":
            return Parser.validateListSchedule(input);
        default:
            return new UnknownCommand(input);
        }
    }

    /**
     * Validates format for ToDos.
     * Returns the validation error, if any.
     *
     * @param input  Input to be validated.
     * @return Command object to be executed.
     * @throws Exception  If invalid input or formatting.
     */
    public static Command validateToDo(String input) throws Exception {
        try {
            Pattern p = Pattern.compile(TODO_REGEX);
            Matcher m = p.matcher(input);
            if (!m.find()) { /* If matching of the TODO_REGEX fails */
                throw new PasonException("Please include a description for your todo task.");
            }

            String description = m.group(2);
            return constructToDoCommand(description);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Constructs the AddCommand object for the ToDo.
     * Returns the new Deadline object if successful, or throws exception.
     *
     * @param input  Description of ToDo
     * @return AddCommand to be executed.
     * @throws PasonException  If invalid input or formatting.
     */
    public static Command constructToDoCommand(String input) {
        ToDo newToDo = new ToDo(input);
        return new AddCommand(input, newToDo);
    }

    /**
     * Validates format for Deadlines.
     * Returns the validation error, if any.
     *
     * @param input  Input to be validated.
     * @return Command object to be executed.
     * @throws PasonException  If invalid input or formatting.
     */
    public static Command validateDeadline(String input) throws Exception {
        try {
            return constructDeadlineCommand(input);
        } catch (DateTimeParseException e) {
            throw new PasonException("Oops! You've entered an invalid date and time format.\n"
                    + "Please use: dd/mm/yyyy hhmm");
        } catch (Exception e) {
            throw new PasonException(e.getMessage());
        }
    }

    /**
     * Constructs the AddCommand object for the Deadline.
     * Returns the new AddCommand object if successful, or throws exception.
     *
     * @param input  Input to be validated.
     * @return AddCommand to be executed.
     * @throws PasonException  If invalid input or formatting.
     */
    public static Command constructDeadlineCommand(String input) throws Exception {
        String[] inputParts = input.substring(8).trim().split(" /by ");

        if (inputParts.length != VALID_DEADLINE_FORMAT_COUNT) {
            throw new PasonException("You've entered an invalid format. "
                    + "Please use: deadline <description> /by <when>");
        }
        String description = inputParts[0];
        String deadline = inputParts[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime deadlineBy = LocalDateTime.parse(deadline, formatter);
        Deadline newDeadline = new Deadline(description, deadlineBy);
        return new AddCommand(input, newDeadline);
    }

    /**
     * Validates format for Events.
     * Returns the validation error, if any.
     *
     * @param input  Input to be validated.
     * @return Command object to be executed.
     * @throws PasonException  If invalid input or formatting.
     */
    public static Command validateEvent(String input) throws Exception {
        try {
            return constructEventCommand(input);
        } catch (DateTimeParseException e) {
            throw new PasonException("Oops! You've entered an invalid date format.\n"
                    + "Please use: dd/mm/yyyy");
        } catch (Exception e) {
            throw new PasonException(e.getMessage());
        }
    }

    /**
     * Constructs the AddCommand object for the Event.
     * Returns the new AddCommand object if successful, or throws exception.
     *
     * @param input  Input to be validated.
     * @return AddCommand to be executed.
     * @throws PasonException  If invalid input or formatting.
     */
    public static Command constructEventCommand(String input) throws Exception {
        String[] inputParts = input.substring(5).trim().split(" /at ");
        if (inputParts.length != VALID_EVENT_FORMAT_COUNT) {
            throw new PasonException("You've entered an invalid format. "
                    + "Please use: event <description> /at <when>");
        }

        String[] dateAndTime = inputParts[1].split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        String description = inputParts[0];
        LocalDate eventDate = LocalDate.parse(dateAndTime[0], formatter);
        Event newEvent = new Event(description, eventDate, parseOptionalEventTime(dateAndTime));
        return new AddCommand(input, newEvent);
    }

    /**
     * Returns the event time if it exists.
     *
     * @param dateAndTime  array
     * @return time, if exists, or null if invalid.
     */
    public static String parseOptionalEventTime(String[] dateAndTime) {
        if (dateAndTime.length != OPTIONAL_TIME_COUNT) {
            return null;
        }
        return dateAndTime[1];
    }

    /**
     * Constructs the AddCommand object for the Event.
     * Returns the new AddCommand object if successful, or throws exception.
     *
     * @param input  Input to be validated.
     * @return AddCommand to be executed.
     * @throws PasonException  If invalid input or formatting.
     */
    public static Command validateListSchedule(String input) throws Exception {
        String[] inputParts = input.trim().split(" ");
        if (inputParts.length != VALID_SCHEDULE_FORMAT_COUNT) {
            throw new PasonException("Please use the format listschedule <dd/mm/yyyy>");
        }
        String parsedDate = Parser.parseDate(inputParts[1]);
        return new ListScheduleCommand(input, parsedDate);
    }

    /**
     * Parses date to correct format.
     */
    public static String parseDate(String date) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            if (date.equals("today")) {
                return LocalDate.now().format(formatter).toString();
            } else if (date.equals("tomorrow")) {
                return LocalDate.now().plusDays(1).format(formatter).toString();
            } else {
                LocalDate parsedDate = LocalDate.parse(date, formatter);
                String formattedString = parsedDate.format(formatter);
                return formattedString;
            }
        } catch (DateTimeParseException e) {
            throw new PasonException("Oops! You've entered an invalid date format.\n"
                    + "Please use: dd/mm/yyyy");
        } catch (Exception e) {
            throw new PasonException(e.getMessage());
        }
    }
}
