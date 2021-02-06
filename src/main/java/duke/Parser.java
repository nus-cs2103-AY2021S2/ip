package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Period;

public class Parser {

    /**
     * Returns a Command with enum type by parsing user input of single word.
     *
     * @param input the string that the user inputs.
     * @return a Command of the enum type.
     */
    public static Command parseCommandForSingleWord(String input) {
        return Command.valueOf(input.toUpperCase(Locale.ROOT));
    }

    /**
     * Returns a Command with enum type by parsing user input of multiple words.
     *
     * @param input the string that the user inputs.
     * @return a Command of the enum type.
     */
    public static Command parseCommandForMultipleWords(String input) {
        int endOfFirstWord = input.indexOf(' ');
        String commandInput = input.substring(0, endOfFirstWord);
        return Command.valueOf(commandInput.toUpperCase(Locale.ROOT));
    }

    /**
     * Returns the rest input apart from the command by parsing user input.
     *
     * @param input the string that the user inputs.
     * @return the rest input.
     */
    public static String parseRestInput(String input) {
        int endOfFirstWord = input.indexOf(' ');
        return input.substring(endOfFirstWord + 1);
    }

    /**
     * Returns a task by parsing description under instruction of command.
     *
     * @param command the Command enum type.
     * @param input input of user.
     * @return the task corresponding to the input.
     * @throws ParseException if parsing fails.
     */
    public static Task parseDescription(Command command, String input) throws ParseException {
        switch (command) {
        case TODO:
            return parseToDo(input);
        case DEADLINE:
            return parseDeadline(input);
        case EVENT:
            return parseEvent(input);
        case PERIOD:
            return parsePeriod(input);
        default:
            throw new ParseException("the first argument command is not valid.");
        }
    }

    /**
     * Returns a ToDo by parsing the input behind the command.
     * Used when the command is todo.
     * Works when the input is not empty.
     *
     * @param input input of users.
     * @return a task of ToDo corresponding to the input.
     * @throws ParseException if the description is empty.
     */
    private static ToDo parseToDo(String input) throws ParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("OOPS!!! The description of a todo cannot be empty.\n");
        }
        if (input.charAt(0) == ' ') {
            input = input.substring(1);
        }
        assert input.isEmpty() : false;
        return new ToDo(input);
    }

    /**
     * Returns a Deadline by parsing the input behind the command.
     * Used when the command is deadline.
     * Works when the input is "{description} /by YYYY-MM-DD".
     *
     * @param input input of users.
     * @return a task of Deadline corresponding to the input.
     * @throws ParseException if the description is empty.
     * @throws DateTimeParseException if the format of data time is not correct.
     */
    private static Deadline parseDeadline(String input) throws ParseException, DateTimeParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("OOPS!!! The description of a deadline cannot be empty.\n");
        }
        if (input.contains("/by ")) {
            if (input.charAt(0) == ' ') {
                input = input.substring(1);
            }
            int endOfDescription = input.indexOf("/by ");
            String description = input.substring(0, endOfDescription);
            String deadline = input.substring(endOfDescription + 4);
            LocalDate date = LocalDate.parse(deadline);
            return new Deadline(description, date);
        } else {
            throw new ParseException("OOPS!!! Please enter '/by YYYY-MM-DD' after description.\n");
        }
    }

    /**
     * Returns an Event by parsing the input behind the command.
     * Used when the command is event.
     * Works when the input is "{description} /at YYYY-MM-DD".
     *
     * @param input input of users.
     * @return a task of Event corresponding to the input.
     * @throws ParseException if the description is empty.
     * @throws DateTimeParseException if the format of data time is not correct.
     */
    private static Event parseEvent(String input) throws ParseException, DateTimeParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("OOPS!!! The description of an event cannot be empty.\n");
        }
        if (input.contains("/at ")) {
            if (input.charAt(0) == ' ') {
                input = input.substring(1);
            }
            assert !input.contains("/at ") : false;
            int endOfDescription = input.indexOf("/at ");
            String description = input.substring(0, endOfDescription);
            String time = input.substring(endOfDescription + 4);
            LocalDate date = LocalDate.parse(time);
            return new Event(description, date);
        } else {
            throw new ParseException("OOPS!!! Please enter '/at YYYY-MM-DD' after description\n");
        }
    }

    /**
     * Returns a Period by parsing the input behind the command.
     * Used when the command is period.
     * Works when the input is "{description} /from YYYY-MM-DD /to YYYY-MM-DD".
     *
     * @param input input of users.
     * @return a task of Period corresponding to the input.
     * @throws ParseException if the description is empty.
     * @throws DateTimeParseException if the format of data time is not correct.
     */
    private static Period parsePeriod(String input) throws ParseException, DateTimeParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("OOPS!!! The description of an event cannot be empty.\n");
        }
        if (input.contains("/from ") && input.contains("/to ")) {
            if (input.charAt(0) == ' ') {
                input = input.substring(1);
            }
            assert !input.contains("/from ") : false;
            int endOfDescription = input.indexOf("/from ");
            int endOfStartTime = input.indexOf("/to ");
            String description = input.substring(0, endOfDescription);
            String startTime = input.substring(endOfDescription + 6, endOfStartTime - 1);
            String endTime = input.substring(endOfStartTime + 4);
            LocalDate startDate = LocalDate.parse(startTime);
            LocalDate endDate = LocalDate.parse(endTime);
            return new Period(description, startDate, endDate);
        } else {
            throw new ParseException("OOPS!!! Please enter '/at YYYY-MM-DD' after description\n");
        }
    }

    /**
     * Parses ToDo stored in the file.
     *
     * @param line one line represents a todo task in the flie.
     * @return a ToDo task.
     */
    public static ToDo parseForToDoInFile(String line) {
        return new ToDo(line.substring(7));
    }

    /**
     * Parses Deadline stored in the file.
     *
     * @param line one line represents a deadline task in the flie.
     * @return a Deadline task.
     */
    public static Task parseForDeadlineInFile(String line) {
        int endOfDescription = line.indexOf("by: ");
        String description = line.substring(7, endOfDescription);
        String deadline = line.substring(endOfDescription + 4, line.length() - 1);
        LocalDate date = LocalDate.parse(deadline);
        return new Deadline(description, date);
    }

    /**
     * Parses Event stored in the file.
     *
     * @param line one line represents an event task in the flie.
     * @return an Event task.
     */
    public static Task parseForEventInFile(String line) {
        int endOfDescription = line.indexOf("at: ");
        String description = line.substring(7, endOfDescription);
        String time = line.substring(endOfDescription + 4, line.length() - 1);
        LocalDate date = LocalDate.parse(time);
        return new Event(description, date);
    }

    /**
     * Parses Period stored in the file.
     *
     * @param line one line represents an event task in the flie.
     * @return an period task.
     */
    public static Task parseForPeriodInFile(String line) {
        int endOfDescription = line.indexOf("from: ");
        int endOfStartTime = line.indexOf("to: ");
        String description = line.substring(7, endOfDescription - 2);
        String startTime = line.substring(endOfDescription + 6, endOfStartTime - 1);
        String endTime = line.substring(endOfStartTime + 4, line.length() - 1);
        LocalDate startDate = LocalDate.parse(startTime);
        LocalDate endDate = LocalDate.parse(endTime);
        return new Period(description, startDate, endDate);
    }

    /**
     * Returns a task corresponding to the line of file stored in the disk.
     *
     * @param line line of the file stored in the disk.
     * @return a task corresponding to the line.
     */
    public static Task parseInFile(String line) {
        Task task;
        if (line.charAt(1) == 'T') {
            task = parseForToDoInFile(line);
        } else if (line.charAt(1) == 'D' && line.contains("by: ")) {
            task = parseForDeadlineInFile(line);
        } else if (line.charAt(1) == 'E' && line.contains("at: ")) {
            task = parseForEventInFile(line);
        } else if (line.charAt(1) == 'P' && line.contains("from: ") && line.contains("to: ")) {
            task = parseForPeriodInFile(line);
        } else {
            throw new ParseException("OOPS!!! It seems there is file corruption.\n");
        }
        if (line.charAt(4) == 'X') {
            task.markedAsDone();
        }
        return task;
    }
}
