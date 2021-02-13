package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.message.Messages;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;

/**
 * The Parser class handles user input, classifies the
 * input based on a command and directs the execution
 * of the command.
 */
public class Parser {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TODO_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)");
    public static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+) /by (?<date>[^/]+)");
    public static final Pattern EVENT_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+) /at (?<dateTime>[^/]+)");
    public static final Pattern INDEX_ARGS_FORMAT = Pattern.compile("(?<index>.+)");
    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    /**
     * Attempts to parse user input into a Command
     * which an instance of Duke can execute.
     * @param userInput The raw string user input.
     * @return The corresponding Command.
     * @throws DukeException Error if user input is not in an appropriate format.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        final Matcher matcher = COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DoneCommand.COMMAND_WORD:
            return prepareDone(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);
        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    private static Command prepareTodo(String arguments) {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new TodoCommand(matcher.group("description"));
    }

    private static Command prepareDeadline(String arguments) {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String description = matcher.group("description");
        final String date = matcher.group("date");

        return new DeadlineCommand(description, date);
    }

    private static Command prepareEvent(String arguments) {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String description = matcher.group("description");
        final String dateTime = matcher.group("dateTime");

        return new EventCommand(description, dateTime);
    }

    private static int parseArgsAsIndex(String arguments) throws DukeException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("index")) - 1;
    }

    private static Command prepareDone(String arguments) {
        try {
            int index = parseArgsAsIndex(arguments);
            return new DoneCommand(index);
        } catch (DukeException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private static Command prepareDelete(String arguments) {
        try {
            int index = parseArgsAsIndex(arguments);
            return new DeleteCommand(index);
        } catch (DukeException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private static Command prepareFind(String arguments) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        return new FindCommand(matcher.group("keywords"));
    }
}
