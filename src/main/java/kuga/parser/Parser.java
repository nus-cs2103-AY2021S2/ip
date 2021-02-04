package kuga.parser;

import static kuga.utils.Messages.MESSAGE_EMPTY_DESCRIPTION;
import static kuga.utils.Messages.MESSAGE_ENTER_COMMAND;
import static kuga.utils.Messages.MESSAGE_FOLLOW_USAGE;
import static kuga.utils.Messages.MESSAGE_INDICATE_TASK;
import static kuga.utils.Messages.MESSAGE_INVALID_COMMAND;
import static kuga.utils.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static kuga.utils.Messages.MESSAGE_INVALID_SYNTAX;
import static kuga.utils.Messages.MESSAGE_INVALID_TASK_INDEX;
import static kuga.utils.Messages.MESSAGE_INVALID_TIME_FORMAT;
import static kuga.utils.Messages.MESSAGE_MISSING_SEARCH_WORD;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kuga.commands.ByeCommand;
import kuga.commands.Command;
import kuga.commands.DeadlineCommand;
import kuga.commands.DeleteCommand;
import kuga.commands.DoneCommand;
import kuga.commands.EventCommand;
import kuga.commands.FindCommand;
import kuga.commands.HelpCommand;
import kuga.commands.InvalidCommandException;
import kuga.commands.InvalidDescriptionException;
import kuga.commands.ListCommand;
import kuga.commands.NoDescriptionException;
import kuga.commands.ToDoCommand;
import kuga.utils.InputDateTimeFormat;

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
     * @param userInput Full user input string.
     * @return A user command.
     * @throws InvalidCommandException     If the user pass in an unrecognized command.
     * @throws InvalidDescriptionException If the format of the arguments do not match the command.
     * @throws NoDescriptionException      If the arguments is empty when further information is required.
     */
    public Command parseCommand(String userInput) throws InvalidCommandException,
            InvalidDescriptionException, NoDescriptionException {
        Matcher matcher = USER_COMMAND_FORMAT.matcher(userInput.strip());

        if (!matcher.matches()) {
            throw new InvalidCommandException(MESSAGE_ENTER_COMMAND);
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
            throw new InvalidCommandException(MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * Parses the arguments for the todo command.
     *
     * @param arguments User input arguments string.
     * @return {@code ToDoCommand}.
     * @throws NoDescriptionException If the description of the task is empty.
     */
    private Command parseArgumentsForToDo(String arguments) throws NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
        return new ToDoCommand(arguments);
    }

    /**
     * Parses the arguments for the deadline command.
     *
     * @param arguments User input arguments string.
     * @return {@code DeadlineCommand}.
     * @throws NoDescriptionException      If the description of the task is empty.
     * @throws InvalidDescriptionException If the format of the deadline is invalid.
     */
    private Command parseArgumentsForDeadline(String arguments) throws NoDescriptionException,
            InvalidDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
        if (!arguments.contains("/by")) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_SYNTAX + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
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
     * Parses the input date string format into a {@code LocalDate} object.
     *
     * @param dateString User input date string.
     * @return {@code LocalDate} object representing the date.
     * @throws InvalidDescriptionException If the format of the date is invalid.
     */
    private static LocalDate parseDate(String dateString) throws InvalidDescriptionException {
        try {
            return LocalDate.parse(dateString, InputDateTimeFormat.INPUT_DATE_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_DATE_FORMAT + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
                    + DeadlineCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses the input time string format into a {@code LocalTime} object.
     *
     * @param timeString User input time string.
     * @return {@code LocalTime} object representing the time.
     * @throws InvalidDescriptionException If the format of the time is invalid.
     */
    private static LocalTime parseTime(String timeString) throws InvalidDescriptionException {
        try {
            return LocalTime.parse(timeString, InputDateTimeFormat.INPUT_TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_TIME_FORMAT + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
                    + DeadlineCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses the arguments for the event command.
     *
     * @param arguments User input arguments string.
     * @return {@code EventCommand}.
     * @throws NoDescriptionException      If the description of the task is empty.
     * @throws InvalidDescriptionException If the format of the event time is invalid.
     */
    private Command parseArgumentsForEvent(String arguments) throws NoDescriptionException,
            InvalidDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_EMPTY_DESCRIPTION);
        }
        if (!arguments.contains("/at")) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_SYNTAX + "\n"
                    + MESSAGE_FOLLOW_USAGE + "\n"
                    + EventCommand.MESSAGE_USAGE);
        }
        String[] eventInputArr = arguments.split("/at");
        String eventTaskName = eventInputArr[0].strip();
        String eventTime = eventInputArr[1].strip();
        return new EventCommand(eventTaskName, eventTime);
    }

    /**
     * Parses the arguments for the done command.
     *
     * @param arguments User input arguments string.
     * @return {@code DoneCommand}.
     * @throws InvalidDescriptionException If the description is not a valid index.
     * @throws NoDescriptionException      If the task number is missing.
     * @throws IndexOutOfBoundsException   If the specified task number is outside of range.
     */
    private Command parseArgumentsForDone(String arguments) throws InvalidDescriptionException,
            NoDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_INDICATE_TASK);
        }
        try {
            int index = Integer.parseInt(arguments.strip()) - 1; // Account for 0-based indexing
            return new DoneCommand(index);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_TASK_INDEX);
        }
    }

    /**
     * Parses the arguments for the delete command.
     *
     * @param arguments User input arguments string.
     * @return {@code DeleteCommand}.
     * @throws InvalidDescriptionException If the description is not a valid index.
     * @throws NoDescriptionException      If the task number is missing.
     * @throws IndexOutOfBoundsException   If the specified task number is outside of range.
     */
    private Command parseArgumentsForDelete(String arguments) throws InvalidDescriptionException,
            NoDescriptionException, IndexOutOfBoundsException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_INDICATE_TASK);
        }
        try {
            int index = Integer.parseInt(arguments.strip()) - 1; // Account for 0-based indexing
            return new DeleteCommand(index);
        } catch (NumberFormatException ex) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_TASK_INDEX);
        }
    }

    /**
     * Parses the arguments for the find command.
     *
     * @param arguments User input arguments string.
     * @return {@code FindCommand}.
     * @throws NoDescriptionException If the search word is missing.
     */
    private Command parseArgumentsForFind(String arguments) throws NoDescriptionException {
        if (arguments.isBlank()) {
            throw new NoDescriptionException(MESSAGE_MISSING_SEARCH_WORD);
        }
        return new FindCommand(arguments);
    }
}
