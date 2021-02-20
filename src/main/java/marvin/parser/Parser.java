package marvin.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import marvin.commands.Command;
import marvin.commands.DeadlineCommand;
import marvin.commands.DeleteCommand;
import marvin.commands.DoneCommand;
import marvin.commands.EventCommand;
import marvin.commands.ExitCommand;
import marvin.commands.FindCommand;
import marvin.commands.IncorrectCommand;
import marvin.commands.ListCommand;
import marvin.commands.TodoCommand;
import marvin.exception.DukeException;
import marvin.message.Messages;

/**
 * Parses user input into an executable command.
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
     * Parses user input into an executable command.
     * @param userInput The raw string user input.
     * @return The corresponding executable command.
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

    /**
     * Parses arguments in the context of the to-do command.
     * @param arguments The raw string arguments of the to-do command.
     * @return The prepared to-do command.
     */
    private static Command prepareTodo(String arguments) {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        return new TodoCommand(matcher.group("description"));
    }

    /**
     * Parses the arguments in the context of the deadline command.
     * @param arguments The raw string arguments of the deadline command.
     * @return The prepared deadline command.
     */
    private static Command prepareDeadline(String arguments) {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String description = matcher.group("description");
        final String date = matcher.group("date");

        return new DeadlineCommand(description, date);
    }

    /**
     * Parses arguments in the context of the event command.
     * @param arguments The raw string arguments of the event command.
     * @return The prepared event command.
     */
    private static Command prepareEvent(String arguments) {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        final String description = matcher.group("description");
        final String dateTime = matcher.group("dateTime");

        return new EventCommand(description, dateTime);
    }

    /**
     * Parses the given raw string arguments as an index integer.
     * @param arguments The raw string arguments.
     * @return The index integer
     * @throws DukeException Error if the raw string arguments cannot be found.
     */
    private static int parseArgsAsIndex(String arguments) throws DukeException {
        final Matcher matcher = INDEX_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("index")) - 1;
    }

    /**
     * Parses the arguments in the context of the done command.
     * @param arguments The raw string arguments of the done command.
     * @return The prepared done command.
     */
    private static Command prepareDone(String arguments) {
        try {
            int index = parseArgsAsIndex(arguments);
            return new DoneCommand(index);
        } catch (DukeException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Parses the arguments in the context of the delete command.
     * @param arguments The raw string arguments of delete the command.
     * @return The prepared delete command.
     */
    private static Command prepareDelete(String arguments) {
        try {
            int index = parseArgsAsIndex(arguments);
            return new DeleteCommand(index);
        } catch (DukeException e) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Parses the arguments in the context of the find command.
     * @param arguments The raw string arguments of the find command.
     * @return The prepared find command.
     */
    private static Command prepareFind(String arguments) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        return new FindCommand(matcher.group("keywords"));
    }
}
