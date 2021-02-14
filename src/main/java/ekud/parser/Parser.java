package ekud.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ekud.command.AddCommand;
import ekud.command.AddDeadlineCommand;
import ekud.command.AddEventCommand;
import ekud.command.AddTodoCommand;
import ekud.command.ByeCommand;
import ekud.command.Command;
import ekud.command.DeleteCommand;
import ekud.command.DoneCommand;
import ekud.command.FindCommand;
import ekud.command.ListCommand;
import ekud.command.ViewCommand;
import ekud.common.exception.EkudException;
import ekud.common.exception.IncompleteDetailException;
import ekud.common.exception.InvalidCommandException;
import ekud.common.exception.InvalidTaskIndexException;
import ekud.common.exception.NoTaskDescriptionException;

/**
 * Command parser
 */
public class Parser {
    private static final Pattern COMMAND_FORMAT = Pattern.compile("^(?<command>\\S+)(?:\\s+(?<arguments>.*))?");
    private static final Pattern TIMED_TASK_ARGS = Pattern.compile("(?<description>.*)\\s+"
            + "(?<separator>/(?:by|at))\\s+"
            + "(?<datetime>.*)");

    /**
     * Create an AddCommand from specified command type and arguments.
     *
     * @param commandType The type of Task to create, namely ToDo, Deadline or Event.
     * @param arguments   All extra arguments necessary for the given task.
     * @return The AddCommand that can be used to construct the task.
     * @throws EkudException If the command type is invalid or any problems with arguments.
     */
    private static AddCommand createAddCommand(CommandType commandType, String arguments) throws EkudException {
        if (arguments.isBlank()) {
            throw new NoTaskDescriptionException();
        }

        if (commandType == CommandType.TODO) {
            return new AddTodoCommand(arguments);
        }

        /* either Deadline or Event */
        final Matcher matcher = TIMED_TASK_ARGS.matcher(arguments);

        if (!matcher.matches()) {
            throw new EkudException("Invalid command format!");
        }

        String dateTimeString = matcher.group("datetime").trim();
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new EkudException("Invalid date and time format, use d/M/yyyy HHmm");
        }

        AddCommand command;
        if (matcher.group("separator").equals("/by")) {
            command = new AddDeadlineCommand(matcher.group("description"), dateTime);
        } else {
            command = new AddEventCommand(matcher.group("description"), dateTime);
        }

        assert command != null : "No add command created!";

        return command;
    }

    private static ViewCommand createViewCommand(String arguments) throws EkudException {
        if (arguments.isBlank()) {
            throw new IncompleteDetailException("date");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(arguments, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new EkudException("Invalid date format, use d/M/yyyy");
        }

        return new ViewCommand(date);
    }

    /**
     * Parse a given line of command into an executable Command.
     *
     * @param fullCommand The String containing the entire command.
     * @return The Command that can be executed to satisfy the fullCommand given.
     * @throws EkudException If any parts of the command are invalid.
     */
    public static Command parse(String fullCommand) throws EkudException {
        final Matcher matcher = COMMAND_FORMAT.matcher(fullCommand.trim());

        if (!matcher.matches()) {
            throw new EkudException("Invalid command format!");
        }

        final String commandWord = matcher.group("command");
        final boolean hasArguments = matcher.group("arguments") == null;
        final String arguments = hasArguments ? "" : matcher.group("arguments").trim();

        CommandType func;
        try {
            func = CommandType.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        int index; // for storing indices, workaround switch case scope issue
        switch (func) {
        case LIST:
            return new ListCommand();

        case DONE:
            try {
                index = Integer.parseInt(arguments) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException();
            }

            return new DoneCommand(index);

        case DELETE:
            try {
                index = Integer.parseInt(arguments) - 1;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidTaskIndexException();
            }

            return new DeleteCommand(index);

        case BYE:
            return new ByeCommand();

        case TODO:
        case DEADLINE:
        case EVENT:
            return createAddCommand(func, arguments);

        case FIND:
            if (arguments.isBlank()) {
                throw new IncompleteDetailException("keyword");
            }

            return new FindCommand(arguments);

        case VIEW:
            if (arguments.isBlank()) {
                throw new IncompleteDetailException("date");
            }

            return createViewCommand(arguments);

        default:
            throw new InvalidCommandException();
        }
    }

    private enum CommandType {
        LIST,
        DONE,
        DELETE,
        BYE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        VIEW
    }
}
