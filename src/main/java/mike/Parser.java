package mike;

import mike.task.Deadline;
import mike.task.Event;
import mike.task.Task;
import mike.task.ToDo;

import java.util.Locale;

/**
 * Parses input from the user
 */
public class Parser {

    /**
     * Returns a Task Object by parsing the description depending on the Command
     *
     * @param command an object of the Command type
     * @param input   the input of the user
     * @return the Task corresponding to the user's command
     * @throws ParseException if parsing fails
     */
    public static Task parseDescription(Command command, String input) throws ParseException {
        switch (command) {
        case TODO:
            return parseToDo(input);
        case DEADLINE:
            return parseDeadline(input);
        case EVENT:
            return parseEvent(input);
        default:
            throw new ParseException("The command is not valid");
        }
    }

    /**
     * Returns a ToDo by parsing the input
     * Used when the command is todo
     *
     * @param input the input from the user
     * @return a ToDo Object based on the input
     * @throws ParseException if the description is empty
     */
    private static ToDo parseToDo(String input) throws ParseException {
        if (input.equals(" ") || input.isEmpty()) {
            throw new ParseException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new ToDo(input);
        }
    }

    /**
     * Returns a Deadline by parsing the input
     * Used when the command is deadline
     *
     * @param input the input from the user
     * @return a Deadline Object based on the input
     * @throws ParseException if the description is empty
     */
    private static Deadline parseDeadline(String input) throws ParseException {
        if (input.equals(" ") || input.isEmpty()) {
            throw new ParseException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (input.contains("/by ")) {
            int endIndexOfDescription = input.indexOf("/by ");
            String deadlineDescription = input.substring(0, endIndexOfDescription);
            String deadline = input.substring(endIndexOfDescription + 4);
            return new Deadline(deadlineDescription, deadline);
        } else {
            throw new ParseException("OOPS!!! Please enter '/by YYYY-MM-DD after the description");
        }
    }

    /**
     * Returns an Event by parsing the input
     * Used when the command is event
     *
     * @param input the input from the user
     * @return a Event Object based on the input from the user
     * @throws ParseException if the description is empty
     */
    private static Event parseEvent(String input) throws ParseException {
        if (input.equals(" ") || input.isEmpty()) {
            throw new ParseException("OOPS!!! The description of an event cannot be empty.");
        } else if (input.contains("/at ")) {
            int endIndexOfDescription = input.indexOf("/at ");
            String eventDescription = input.substring(0, endIndexOfDescription);
            String eventTime = input.substring(endIndexOfDescription + 4);
            return new Event(eventDescription, eventTime);
        } else {
            throw new ParseException("OOPS!!! Please enter '/at YYYY-MM-DD after the description");
        }
    }

    /**
     * Returns a Task that is parsed from a String
     * Used to parse lines from a file for tasks
     *
     * @param line the String to be parsed for tasks
     * @return a Task Object created from line
     */
    public static Task parseLineInFile(String line) {
        Task task;
        if (line.charAt(1) == 'T') {
            task = parseForToDoInFile(line);
        } else if (line.charAt(1) == 'D') {
            task = parseForDeadlineInFile(line);
        } else if (line.charAt(1) == 'E') {
            task = parseForEventInFile(line);
        } else {
            throw new ParseException("OOPS!!! It seems that the file might be corrupted.");
        }
        if (line.charAt(4) == '\u2713') {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parse a single-word user input and returns a Command
     *
     * @param input user input as a String
     * @return a Command of the enum type
     */
    public static Command parseCommandForSingleWord(String input) {
        return Command.valueOf(input.toUpperCase(Locale.ROOT));
    }

    /**
     * Parse a multiple-words user input and return a Command
     *
     * @param input user input as a String
     * @return a Command of the enum type
     */
    public static Command parseCommandForMultipleWords(String input) {
        int endIndexOfCommand = input.indexOf(' ');
        String commandInput = input.substring(0, endIndexOfCommand);
        return Command.valueOf(commandInput.toUpperCase(Locale.ROOT));
    }

    /**
     * Returns the description portion of the user input
     *
     * @param input user input as a String
     * @return the description portion of the user input as a String
     */
    public static String parseDescriptionInput(String input) {
        int endIndexOfCommand = input.indexOf(' ');
        return input.substring(endIndexOfCommand + 1);
    }

    /**
     * Parses ToDo Tasks stored in a file
     *
     * @param line line representing a ToDo Task in the file
     * @return a ToDo Task
     */
    private static ToDo parseForToDoInFile(String line) {
        return new ToDo(line.substring(6));
    }

    /**
     * parses Deadline Tasks stored in a file
     *
     * @param line line representing a Deadline Task in the file
     * @return a Deadline Task
     */
    private static Deadline parseForDeadlineInFile(String line) {
        int endIndexOfDescription = line.indexOf("by: ");
        String deadlineDescription = line.substring(6, endIndexOfDescription - 2);
        String deadline = line.substring(endIndexOfDescription + 4, line.length() - 1);
        return new Deadline(deadlineDescription, deadline);
    }

    /**
     * Parses Event Tasks stored in a file
     *
     * @param line line representing a Event Task in the file
     * @return a Event Task
     */
    private static Event parseForEventInFile(String line) {
        int endIndexOfDescription = line.indexOf("at: ");
        String eventDescription = line.substring(6, endIndexOfDescription - 2);
        String eventTime = line.substring(endIndexOfDescription + 4, line.length() - 1);
        return new Event(eventDescription, eventTime);
    }
}
