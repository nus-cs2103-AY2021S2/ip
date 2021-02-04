package duke.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.CommandNotFoundException;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.task.Deadline;
import duke.task.EnumTask;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * A class represents a Parser.
 */
public class Parser {
    /**
     * Returns a Command based on user's input.
     * @param input The full command from user's input.
     * @return A command base on the input.
     * @throws DukeException If error occurs during the process.
     */
    public static Command parseCommand(String input) throws DukeException {
        int endIndex = input.indexOf(" ");
        String potentialCommand = (endIndex == -1) ? input : input.substring(0, endIndex);
        Command command;

        if (potentialCommand.equalsIgnoreCase("TODO")) {
            String description = input.substring(endIndex).strip();
            command = new TodoCommand(description);
        } else if (potentialCommand.equalsIgnoreCase("DEADLINE")) {
            String[] descriptions = input.substring(endIndex).strip().split("/by");
            command = new DeadlineCommand(descriptions);
        } else if (potentialCommand.equalsIgnoreCase("EVENT")) {
            String[] descriptions = input.substring(endIndex).strip().split("/at");
            command = new EventCommand(descriptions);
        } else if (potentialCommand.equalsIgnoreCase("LIST")) {
            command = new ListCommand();
        } else if (potentialCommand.equalsIgnoreCase("DONE")) {
            command = new DoneCommand(input);
        } else if (potentialCommand.equalsIgnoreCase("BYE")) {
            command = new ExitCommand();
        } else if (potentialCommand.equalsIgnoreCase("DELETE")) {
            command = new DeleteCommand(input);
        } else if (potentialCommand.equalsIgnoreCase("FIND")) {
            command = new FindCommand(input);
        } else {
            throw new CommandNotFoundException("What do you mean? I do not know this command.");
        }
        return command;
    }

    public static Task parseTask(EnumTask taskType, String ... descriptions) throws DukeException {
        String name = descriptions[0];
        switch (taskType) {
        case TODO:
            return new Todo(name);
        case DEADLINE:
            String deadline = descriptions[1].strip();
            LocalDateTime cutOffTime = parseDateTime(deadline);
            return new Deadline(name, cutOffTime);
        case EVENT:
            String eventTime = descriptions[1].strip();
            LocalDateTime startingTime = parseDateTime(eventTime);
            return new Event(name, startingTime);
        default:
            throw new DukeException("I do not know this task");
        }
    }

    /**
     * Returns a LocalDateTime from a string containing date and time.
     * @param dateTime The input string containing date and time.
     * @return A LocalDateTime parsed from the input string.
     * @throws InvalidDateTimeException If the input date and time string is not valid.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws InvalidDateTimeException {
        String[] dateAndTime = dateTime.strip().split(" ");

        LocalDateTime parsedDateTime;

        if (dateAndTime.length != 2) {
            throw new InvalidDateTimeException("Input date and time is not complete.");
        }
        String date = dateAndTime[0].strip();
        String time = dateAndTime[1].strip();
        parsedDateTime = LocalDateTime.of(parseDate(date), parseTime(time));
        return parsedDateTime;
    }

    private static LocalDate parseDate(String date) throws InvalidDateTimeException {
        String[] yearMonthDay = date.strip().split("-");
        if (yearMonthDay.length != 3) {
            throw new InvalidDateTimeException("Input date is not valid.");
        }
        String year = yearMonthDay[0].strip();
        String month = yearMonthDay[1].strip();
        String day = yearMonthDay[2].strip();
        if (year.length() == 4 && month.length() == 2 && day.length() == 2) {
            try {
                return LocalDate.parse(date);
            } catch (DateTimeException e) {
                throw new InvalidDateTimeException("Input date is not valid.");
            }
        } else {
            throw new InvalidDateTimeException("Input date is not valid.");
        }
    }

    private static LocalTime parseTime(String time) throws InvalidDateTimeException {
        String hour = time.strip().substring(0, 2);
        String minute = time.strip().substring(2, 4);
        try {
            return LocalTime.parse(hour + ":" + minute);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Input time is not valid.");
        }
    }
}
