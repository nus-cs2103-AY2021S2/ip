package quackers.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import quackers.QuackersException;
import quackers.command.AddDeadlineCommand;
import quackers.command.AddEventCommand;
import quackers.command.AddTodoCommand;
import quackers.command.ByeCommand;
import quackers.command.Command;
import quackers.command.DeleteTaskCommand;
import quackers.command.FindTasksCommand;
import quackers.command.ListTasksCommand;
import quackers.command.MarkTaskAsDoneCommand;
import quackers.command.MarkTaskAsUndoneCommand;
import quackers.command.StatsCommand;
import quackers.command.UsageCommand;
import quackers.ui.Ui;

/**
 * Represents the command parser.
 */
public class CommandParser {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Pattern COMMAND_TYPE_PATTERN = Pattern.compile(
            "usage|bye|list|find|stats|todo|deadline|event|delete|done");
    private static final Pattern USAGE_PATTERN = Pattern.compile("usage");
    private static final Pattern BYE_PATTERN = Pattern.compile("bye");
    private static final Pattern LIST_TASKS_PATTERN = Pattern.compile("list");
    private static final Pattern FIND_TASKS_PATTERN = Pattern.compile("find ([a-zA-Z0-9\\s]+)");
    private static final Pattern STATS_PATTERN = Pattern.compile("stats");
    private static final Pattern ADD_TODO_PATTERN = Pattern.compile("todo ([a-zA-Z0-9\\s]+)");
    private static final Pattern ADD_DEADLINE_PATTERN = Pattern.compile(
            "deadline ([a-zA-Z0-9\\s]+) /by (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
    private static final Pattern ADD_EVENT_PATTERN = Pattern.compile(
            "event ([a-zA-Z0-9\\s]+) /at (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
    private static final Pattern DELETE_TASK_PATTERN = Pattern.compile("delete ([0-9]+)");
    private static final Pattern MARK_TASK_AS_DONE_PATTERN = Pattern.compile("done ([0-9]+)");
    private static final Pattern MARK_TASK_AS_UNDONE_PATTERN = Pattern.compile("undone ([0-9]+)");

    /**
     * Parses raw command text input and generate the specific Command object.
     *
     * @param input Raw command text input.
     * @return The actual parsed command object.
     * @throws QuackersException If invalid text input specified.
     */
    public static Command getCommand(String input) throws QuackersException {
        String commandType = CommandParser.getCommandType(input);

        switch (commandType) {
        case "usage":
            return CommandParser.getUsageCommand(input);
        case "bye":
            return CommandParser.getByeCommand(input);
        case "list":
            return CommandParser.getListTasksCommand(input);
        case "find":
            return CommandParser.getFindTasksCommand(input);
        case "stats":
            return CommandParser.getStatsCommand(input);
        case "todo":
            return CommandParser.getAddTodoCommand(input);
        case "deadline":
            return CommandParser.getAddDeadlineCommand(input);
        case "event":
            return CommandParser.getAddEventCommand(input);
        case "delete":
            return CommandParser.getDeleteTaskCommand(input);
        case "done":
            return CommandParser.getMarkTaskAsDoneCommand(input);
        case "undone":
            return CommandParser.getMarkTaskAsUndoneCommand(input);
        default:
            throw new QuackersException(Ui.getInvalidCommand());
        }
    }

    /**
     * Retrieves the command type from the text input.
     *
     * @param input Raw command text input.
     * @return Command type.
     * @throws QuackersException If there are no supported command types found.
     */
    private static String getCommandType(String input) throws QuackersException {
        String commandType = input.split(" ")[0].toLowerCase();
        Matcher m = CommandParser.COMMAND_TYPE_PATTERN.matcher(commandType);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return commandType;
    }

    /**
     * Retrieves the UsageCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return UsageCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static UsageCommand getUsageCommand(String input) throws QuackersException {
        Matcher m = CommandParser.USAGE_PATTERN.matcher(input);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return new UsageCommand();
    }

    /**
     * Retrieves the ByeCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return ByeCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static ByeCommand getByeCommand(String input) throws QuackersException {
        Matcher m = CommandParser.BYE_PATTERN.matcher(input);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return new ByeCommand();
    }

    /**
     * Retrieves the ListTasksCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return ListTasksCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static ListTasksCommand getListTasksCommand(String input) throws QuackersException {
        Matcher m = CommandParser.LIST_TASKS_PATTERN.matcher(input);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return new ListTasksCommand();
    }

    /**
     * Retrieves the FindTasksCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return FindTasksCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static FindTasksCommand getFindTasksCommand(String input) throws QuackersException {
        Matcher m = CommandParser.FIND_TASKS_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        String keyword = m.group(1);
        return new FindTasksCommand(keyword);
    }

    /**
     * Retrieves the StatsCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return StatsCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static StatsCommand getStatsCommand(String input) throws QuackersException {
        Matcher m = CommandParser.STATS_PATTERN.matcher(input);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return new StatsCommand();
    }

    /**
     * Retrieves the AddTodoCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return AddTodoCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static AddTodoCommand getAddTodoCommand(String input) throws QuackersException {
        Matcher m = CommandParser.ADD_TODO_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        String desc = m.group(1);
        return new AddTodoCommand(desc);
    }

    /**
     * Retrieves the AddDeadlineCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return AddDeadlineCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static AddDeadlineCommand getAddDeadlineCommand(String input) throws QuackersException {
        Matcher m = CommandParser.ADD_DEADLINE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 2) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        String desc = m.group(1);
        LocalDateTime dateTime = LocalDateTime.parse(m.group(2), CommandParser.DATETIME_FORMATTER);
        return new AddDeadlineCommand(desc, dateTime);
    }

    /**
     * Retrieves the AddEventCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return AddEventCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static AddEventCommand getAddEventCommand(String input) throws QuackersException {
        Matcher m = CommandParser.ADD_EVENT_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 2) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        String desc = m.group(1);
        LocalDateTime dateTime = LocalDateTime.parse(m.group(2), CommandParser.DATETIME_FORMATTER);
        return new AddEventCommand(desc, dateTime);
    }

    /**
     * Retrieves the DeleteTaskCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return DeleteTaskCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static DeleteTaskCommand getDeleteTaskCommand(String input) throws QuackersException {
        Matcher m = CommandParser.DELETE_TASK_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        int index = Integer.parseInt(m.group(1));
        return new DeleteTaskCommand(index);
    }

    /**
     * Retrieves the MarkTaskAsDoneCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return MarkTaskAsDoneCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static MarkTaskAsDoneCommand getMarkTaskAsDoneCommand(String input) throws QuackersException {
        Matcher m = CommandParser.MARK_TASK_AS_DONE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        int index = Integer.parseInt(m.group(1));
        return new MarkTaskAsDoneCommand(index);
    }

    /**
     * Retrieves the MarkTaskAsUndoneCommand object from the text input.
     *
     * @param input Raw command text input.
     * @return MarkTaskAsUndoneCommand object.
     * @throws QuackersException If invalid text input specified.
     */
    private static MarkTaskAsUndoneCommand getMarkTaskAsUndoneCommand(String input) throws QuackersException {
        Matcher m = CommandParser.MARK_TASK_AS_UNDONE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        int index = Integer.parseInt(m.group(1));
        return new MarkTaskAsUndoneCommand(index);
    }
}
