package pason.parser;

import pason.commands.*;
import pason.exceptions.PasonException;
import pason.tasks.Deadline;
import pason.tasks.Event;
import pason.tasks.ToDo;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class for validating commands and inputs.
 */
public class Parser {
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
                return new DeleteCommand(input,  Integer.parseInt(splitInput[1]));
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
            Pattern p = Pattern.compile("(todo) ([\\w ]*)");
            Matcher m = p.matcher(input);
            if (!m.find()) {
                throw new PasonException("Please include a description for your todo task.");
            }
            ToDo newToDo = new ToDo(m.group(2));
            return new AddCommand(input, newToDo);
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
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
            String[] splitInput;
            splitInput = input.substring(8).trim().split(" /by ");
            if (splitInput.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime deadlineBy = LocalDateTime.parse(splitInput[1], formatter);
                Deadline newDeadline = new Deadline(splitInput[0], deadlineBy);
                return new AddCommand(input, newDeadline);
            } else if (splitInput.length == 1) {
                throw new PasonException("Please enter a by date for '"
                        + splitInput[0] + "'");
            } else if (splitInput.length == 0) {
                throw new PasonException("Please enter a description followed by the date in the format: "
                        + "deadline <description> /by <when>");
            } else {
                throw new PasonException("You've entered an invalid format. "
                        + "Please use: deadline <description> /by <when>");
            }
        } catch(DateTimeParseException e) {
            throw new PasonException("Oops! You've entered an invalid date and time format.\n"
                    + "Please use: dd/mm/yyyy hh:mm");
        } catch(Exception e) {
            throw new PasonException(e.getMessage());
        }
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
            String[] splitInput;
            splitInput = input.substring(5).trim().split(" /at ");
            if (splitInput.length == 2) {
                String[] dateAndTime = splitInput[1].split(" ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate eventDate = LocalDate.parse(dateAndTime[0], formatter);
                Event newEvent = new Event(splitInput[0], eventDate,
                        (dateAndTime.length == 1 ? null : dateAndTime[1]));
                return new AddCommand(input, newEvent);
            } else if (splitInput.length == 1) {
                System.out.println(splitInput[0]);
                throw new PasonException("Please enter an at date for '"
                        + splitInput[0] + "'");
            } else if (splitInput.length == 0) {
                throw new PasonException("Please enter a description followed by the date in the format: "
                        + "event <description> /at <when>");
            } else {
                throw new PasonException("You've entered an invalid format. "
                        + "Please use: event <description> /at <when>");
            }
        } catch(DateTimeParseException e) {
            throw new PasonException("Oops! You've entered an invalid date format.\n"
                    + "Please use: dd/mm/yyyy");
        } catch(Exception e) {
            throw new PasonException(e.getMessage());
        }
    }
}
