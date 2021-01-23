package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Parser {
    public static Command parseCommand(String input) {
        return Command.valueOf(input.toUpperCase(Locale.ROOT));
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
                throw new ParseException("the first argument command is not valid.");
        }
    }

    public static ToDo parseToDo(String input) throws ParseException {
        if (input.isEmpty() || input.equals(" "))
            throw new ParseException("OOPS!!! The description of a todo cannot be empty.\n");
        return new ToDo(input);
    }

    public static Deadline parseDeadline(String input) throws ParseException, DateTimeParseException {
        if (input.isEmpty() || input.equals(" "))
            throw new ParseException("OOPS!!! The description of a deadline cannot be empty.\n");
        if (input.contains("/by ")) {
            int endOfDescription = input.indexOf("/by ");
            String description = input.substring(0, endOfDescription);
            String deadline = input.substring(endOfDescription + 4);
            LocalDate date = LocalDate.parse(deadline);
            return new Deadline(description, date);
        } else {
            throw new ParseException("OOPS!!! Please enter '/by YYYY-MM-DD' after description.\n");
        }
    }

    public static Event parseEvent(String input) throws ParseException, DateTimeParseException {
        if (input.isEmpty() || input.equals(" "))
            throw new ParseException("OOPS!!! The description of an event cannot be empty.\n");
        if (input.contains("/at ")) {
            int endOfDescription = input.indexOf("/at ");
            String description = input.substring(0, endOfDescription);
            String time = input.substring(endOfDescription + 4);
            LocalDate date = LocalDate.parse(time);
            return new Event(description, date);
        } else {
            throw new ParseException("OOPS!!! Please enter '/at YYYY-MM-DD' after description\n");
        }
    }

    public static Task parseInFile(String line) {
        Task task;
        if (line.charAt(1) == 'T') {
            task = new ToDo(line.substring(8));
        } else if (line.charAt(1) == 'D' && line.contains("by: ")) {
            int endOfDescription = line.indexOf("by: ");
            String description = line.substring(8, endOfDescription);
            String deadline = line.substring(endOfDescription + 4, line.length() - 1);
            LocalDate date = LocalDate.parse(deadline);
            task = new Deadline(description, date);
        } else if (line.charAt(1) == 'E' && line.contains("at: ")) {
            int endOfDescription = line.indexOf("at: ");
            String description = line.substring(8, endOfDescription);
            String time = line.substring(endOfDescription + 4, line.length() - 1);
            LocalDate date = LocalDate.parse(time);
            task = new Event(description, date);
        } else {
            throw new ParseException("OOPS!!! It seems there is file corruption.\n");
        }
        if (line.charAt(4) == 'X') {
            task.markedAsDone();
        }
        return task;
    }
}
