package mike;

import mike.task.Deadline;
import mike.task.Event;
import mike.task.Task;
import mike.task.ToDo;

public class Parser {
    public static Command parseCommand(String input) {
        return Command.valueOf(input.toUpperCase());
    }

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

    private static ToDo parseToDo(String input) throws ParseException {
        if (input.equals(" ") || input.isEmpty()) {
            throw new ParseException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new ToDo(input);
        }
    }

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

    public static Task parseLineInFile(String line) {
        Task task;
        if (line.charAt(1) == 'T') {
            task = new ToDo(line.substring(8));
        } else if (line.charAt(1) == 'D') {
            int endIndexOfDescription = line.indexOf("/by ");
            String deadlineDescription = line.substring(0, endIndexOfDescription);
            String deadline = line.substring(endIndexOfDescription + 4);
            task = new Deadline(deadlineDescription, deadline);
        } else if (line.charAt(1) == 'E') {
            int endIndexOfDescription = line.indexOf("/at ");
            String eventDescription = line.substring(0, endIndexOfDescription);
            String eventTime = line.substring(endIndexOfDescription + 4);
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
