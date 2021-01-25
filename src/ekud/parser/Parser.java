package ekud.parser;

import java.time.*;
import java.time.format.*;
import java.util.regex.*;

import ekud.command.*;
import ekud.common.exception.*;

public class Parser {
    private static final Pattern COMMAND_FORMAT = Pattern.compile("^(?<command>\\S+)(?:\\s+(?<arguments>.*))?");
    private static final Pattern TIMED_TASK_ARGS = Pattern.compile(
            "(?<description>.*)\\s+"
                    + "(?<separator>/(?:by|at))\\s+"
                    + "(?<datetime>.*)");
    private static final Pattern DATE_TIME_FORMAT = Pattern.compile("^[0-3]?\\d/[0-1]?\\d/\\d{4}\\s+\\d{4}");

    private static AddCommand createAddCommand(CommandType commandType, String arguments) throws DukeException {
        if (arguments.isBlank()) {
            throw new NoTaskDescriptionException();
        }

        AddCommand command;
        if (commandType == CommandType.TODO) {
            command = new AddTodoCommand(arguments);
        } else {
            final Matcher matcher = TIMED_TASK_ARGS.matcher(arguments);

            if (!matcher.matches()) {
                throw new DukeException("Invalid command format!");
            }

            String dateTimeString = matcher.group("datetime").trim();
            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date and time format, use d/M/yyyy HHmm");
            }

            if (matcher.group("separator").equals("/by")) {
                command = new AddDeadlineCommand(matcher.group("description"), dateTime);
            } else {
                command = new AddEventCommand(matcher.group("description"), dateTime);
            }
        }

        return command;
    }

    public static Command parse(String fullCommand) throws DukeException {
        final Matcher matcher = COMMAND_FORMAT.matcher(fullCommand.trim());

        if (!matcher.matches()) {
            throw new DukeException("Invalid command format!");
        }

        final String commandWord = matcher.group("command");
        final String arguments = matcher.group("arguments") == null ? "" : matcher.group("arguments").trim();

        CommandType func;
        try {
            func = CommandType.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        switch (func) {
        case LIST:
            if (arguments.isBlank()) {
                return new ListCommand();
            }

            LocalDate date;
            try {
                date = LocalDate.parse(arguments, DateTimeFormatter.ofPattern("d/M/yyyy"));
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date format, use d/M/yyyy");
            }
            return new ListCommand(date);

        case DONE:
            try {
                int index = Integer.parseInt(arguments) - 1;
                return new DoneCommand(index);
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException();
            }

        case DELETE:
            try {
                int index = Integer.parseInt(arguments) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidTaskIndexException();
            }

        case BYE:
            return new ByeCommand();

        case TODO:
        case DEADLINE:
        case EVENT:
            if (arguments.isBlank()) {
                throw new NoTaskDescriptionException();
            }
            return createAddCommand(func, arguments);

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
        EVENT
    }
}
