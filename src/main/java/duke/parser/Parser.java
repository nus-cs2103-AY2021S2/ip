package duke.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommandException;
import duke.commands.InvalidDescriptionException;
import duke.commands.ListCommand;
import duke.commands.NoDescriptionException;
import duke.commands.ToDoCommand;

import duke.utils.InputDateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Regex to separate the command word and arguments.
     */
    public static final Pattern USER_COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into a command.
     *
     * @param userInput full user input string
     * @return user command
     * @throws InvalidCommandException     if the user pass in an unrecognized command
     * @throws InvalidDescriptionException if the format of the arguments do not match the command
     * @throws NoDescriptionException      if the arguments is empty when further information is required
     */
    public Command parseCommand(String userInput) throws InvalidCommandException,
            InvalidDescriptionException, NoDescriptionException {
        Matcher matcher = USER_COMMAND_FORMAT.matcher(userInput.strip());

        if (!matcher.matches()) {
            throw new InvalidCommandException("Please enter a command.");
        }

        // Get the command word as captured by the named-capturing group
        String command = matcher.group("command");
        // Get the arguments as captured by the named-capturing group
        String arguments = matcher.group("arguments").strip();

        switch (command) {
        case ToDoCommand.COMMAND_WORD:
            return parseArgumentsForToDo(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return parseArgumentsForDeadline(arguments);
        case EventCommand.COMMAND_WORD:
            return parseArgumentsForEvent(arguments);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case DoneCommand.COMMAND_WORD:
            return parseArgumentsForDone(arguments);
        case DeleteCommand.COMMAND_WORD:
            return parseArgumentsForDelete(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case FindCommand.COMMAND_WORD:
            return parseArgumentsForFind(arguments);
        default:
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the arguments for the todo command.
     *
     * @param arguments user input arguments string
     * @return {@code ToDoCommand}
     * @throws NoDescriptionException if the description of the task is empty
     */
    private Command parseArgumentsForToDo(String arguments) throws NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
        return new ToDoCommand(arguments);
    }

    /**
     * Parses the arguments for the deadline command.
     *
     * @param arguments user input arguments string
     * @return {@code DeadlineCommand}
     * @throws NoDescriptionException      if the description of the task is empty
     * @throws InvalidDescriptionException if the format of the deadline is invalid
     */
    private Command parseArgumentsForDeadline(String arguments) throws NoDescriptionException,
            InvalidDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
        if (!arguments.contains("/by")) {
            throw new InvalidDescriptionException("Invalid description syntax. "
                    + "Please follow the usage as shown below:\n"
                    + DeadlineCommand.MESSAGE_USAGE);
        }
        // Split the user input into the task name and the datetime string
        String[] deadlineInputArr = arguments.split("/by", 2);
        String deadlineTaskName = deadlineInputArr[0].strip();
        String userInputDateTime = deadlineInputArr[1].strip();

        // Split the datetime string into a date string and a time string (if available)
        String[] userInputDateTimeArr = userInputDateTime.split("\\s*,*\\s+|\\s*,\\s*", 2);
        String userInputDate = userInputDateTimeArr[0].strip();
        LocalDate deadlineDate = parseDate(userInputDate);

        if (userInputDateTimeArr.length == 1) {
            // User did not enter a time string
            return new DeadlineCommand(deadlineTaskName, deadlineDate);
        } else {
            // User entered a time string
            String userInputTime = userInputDateTimeArr[1].strip();
            LocalTime deadlineTime = parseTime(userInputTime);
            return new DeadlineCommand(deadlineTaskName, deadlineDate, deadlineTime);
        }
    }

    /**
     * Parses the input date string format into a {@code LocalDate} object
     *
     * @param dateString user input date string
     * @return {@code LocalDate} object representing the date
     * @throws InvalidDescriptionException if the format of the date is invalid
     */
    private static LocalDate parseDate(String dateString) throws InvalidDescriptionException {
        try {
            return LocalDate.parse(dateString, InputDateTimeFormat.INPUT_DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new InvalidDescriptionException("Unable to parse date. "
                    + "Please follow the usage as shown below:\n"
                    + DeadlineCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses the input time string format into a {@code LocalTime} object
     *
     * @param timeString user input time string
     * @return {@code LocalTime} object representing the time
     * @throws InvalidDescriptionException if the format of the time is invalid
     */
    private static LocalTime parseTime(String timeString) throws InvalidDescriptionException {
        try {
            return LocalTime.parse(timeString, InputDateTimeFormat.INPUT_TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new InvalidDescriptionException("Unable to parse time. "
                    + "Please follow the usage as shown below:\n"
                    + DeadlineCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses the arguments for the event command.
     *
     * @param arguments user input arguments string
     * @return {@code EventCommand}
     * @throws NoDescriptionException      if the description of the task is empty
     * @throws InvalidDescriptionException if the format of the event time is invalid
     */
    private Command parseArgumentsForEvent(String arguments) throws NoDescriptionException,
            InvalidDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
        if (!arguments.contains("/at")) {
            throw new InvalidDescriptionException("Invalid description syntax. "
                    + "Please follow the usage as shown below:\n"
                    + "Usage: event <task_description> /at <event_time>");
        }
        String[] eventInputArr = arguments.split("/at");
        String eventTaskName = eventInputArr[0].strip();
        String eventTime = eventInputArr[1].strip();
        return new EventCommand(eventTaskName, eventTime);
    }

    /**
     * Parses the arguments for the done command.
     *
     * @param arguments user input arguments string
     * @return {@code DoneCommand}
     * @throws InvalidDescriptionException if the description is not a valid index
     * @throws NoDescriptionException      if the description of the task is empty
     * @throws IndexOutOfBoundsException   if the specified task number is outside of range
     */
    private Command parseArgumentsForDone(String arguments) throws InvalidDescriptionException,
            NoDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("Please indicate a task number to be marked as done.");
        }
        try {
            int index = Integer.parseInt(arguments.strip()) - 1;  // Account for 0-based indexing
            return new DoneCommand(index);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("Please enter a valid task number");
        }
    }

    /**
     * Parses the arguments for the delete command.
     *
     * @param arguments user input arguments string
     * @return {@code DeleteCommand}
     * @throws InvalidDescriptionException if the description is not a valid index
     * @throws NoDescriptionException      if the description of the task is empty
     * @throws IndexOutOfBoundsException   if the specified task number is outside of range
     */
    private Command parseArgumentsForDelete(String arguments) throws InvalidDescriptionException,
            NoDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("Please indicate a task number to be marked as done.");
        }
        try {
            int index = Integer.parseInt(arguments.strip()) - 1;  // Account for 0-based indexing
            return new DeleteCommand(index);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException("Please enter a valid task number");
        }
    }

    private Command parseArgumentsForFind(String arguments) throws NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException("Please enter a search word or phrase!");
        }
        return new FindCommand(arguments);
    }
}
