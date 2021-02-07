package mike;

import mike.task.Deadline;
import mike.task.Event;
import mike.task.Task;
import mike.task.ToDo;

/**
 * Parses input from the user
 */
public class Parser {

    /**
     * Returns a Command with the enum type by parsing the input
     *
     * @param input String object representing the user's input
     * @return a Command of the enum type
     */
    public static Command parseCommand(String input) {
        return Command.valueOf(input.toUpperCase());
    }

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
        }
        assert !input.isEmpty() : "Input cannot be empty";
        return new ToDo(input);

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
            assert !deadlineDescription.isEmpty() : "Description of a deadline should not empty";
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
            assert !eventDescription.isEmpty() : "Description of an event cannot be empty";
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
            task = new ToDo(line.substring(6));
        } else if (line.charAt(1) == 'D') {
            int endIndexOfDescription = line.indexOf("by: ");
            String deadlineDescription = line.substring(6, endIndexOfDescription - 3);
            String deadline = line.substring(endIndexOfDescription + 4, line.length() - 1);
            task = new Deadline(deadlineDescription, deadline);
        } else if (line.charAt(1) == 'E') {
            int endIndexOfDescription = line.indexOf("at: ");
            String eventDescription = line.substring(6, endIndexOfDescription - 3);
            String eventTime = line.substring(endIndexOfDescription + 4, line.length() - 1);
            task = new Event(eventDescription, eventTime);
        } else {
            throw new ParseException("OOPS!!! It seems that the file might be corrupted.");
        }
        if (line.charAt(4) == 'X') {
            task.markAsDone();
        }
        return task;
    }

}
