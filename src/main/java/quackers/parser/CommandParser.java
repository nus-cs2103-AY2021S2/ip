package quackers.parser;

import quackers.QuackersException;
import quackers.ui.Ui;
import quackers.command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    private static final DateTimeFormatter DATETIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Pattern COMMAND_TYPE_PATTERN
            = Pattern.compile("usage|bye|list|find|stats|todo|deadline|event|delete|done");
    private static final Pattern USAGE_PATTERN = Pattern.compile("usage");
    private static final Pattern BYE_PATTERN = Pattern.compile("bye");
    private static final Pattern LIST_TASKS_PATTERN = Pattern.compile("list");
    private static final Pattern FIND_TASKS_PATTERN = Pattern.compile("find ([a-zA-Z0-9\\s]+)");
    private static final Pattern STATS_PATTERN = Pattern.compile("stats");
    private static final Pattern ADD_TODO_PATTERN = Pattern.compile("todo ([a-zA-Z0-9\\s]+)");
    private static final Pattern ADD_DEADLINE_PATTERN
            = Pattern.compile("deadline ([a-zA-Z0-9\\s]+) /by (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
    private static final Pattern ADD_EVENT_PATTERN
            = Pattern.compile("event ([a-zA-Z0-9\\s]+) /at (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
    private static final Pattern DELETE_TASK_PATTERN = Pattern.compile("delete ([0-9]+)");
    private static final Pattern MARK_TASK_AS_DONE_PATTERN = Pattern.compile("done ([0-9]+)");
    private static final Pattern MARK_TASK_AS_UNDONE_PATTERN = Pattern.compile("undone ([0-9]+)");

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

    private static String getCommandType(String input) throws QuackersException {
        String commandType = input.split(" ")[0].toLowerCase();
        Matcher m = CommandParser.COMMAND_TYPE_PATTERN.matcher(commandType);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return commandType;
    }

    private static UsageCommand getUsageCommand(String input) throws QuackersException {
        Matcher m = CommandParser.USAGE_PATTERN.matcher(input);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return new UsageCommand();
    }

    private static ByeCommand getByeCommand(String input) throws QuackersException {
        Matcher m = CommandParser.BYE_PATTERN.matcher(input);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return new ByeCommand();
    }

    private static ListTasksCommand getListTasksCommand(String input) throws QuackersException {
        Matcher m = CommandParser.LIST_TASKS_PATTERN.matcher(input);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return new ListTasksCommand();
    }

    private static FindTasksCommand getFindTasksCommand(String input) throws QuackersException {
        Matcher m = CommandParser.FIND_TASKS_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        String keyword = m.group(1);
        return new FindTasksCommand(keyword);
    }

    private static StatsCommand getStatsCommand(String input) throws QuackersException {
        Matcher m = CommandParser.STATS_PATTERN.matcher(input);
        if (!m.find()) {
            throw new QuackersException(Ui.getInvalidCommand());
        }
        return new StatsCommand();
    }

    private static AddTodoCommand getAddTodoCommand(String input) throws QuackersException {
        Matcher m = CommandParser.ADD_TODO_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        String desc = m.group(1);
        return new AddTodoCommand(desc);
    }

    private static AddDeadlineCommand getAddDeadlineCommand(String input) throws QuackersException {
        Matcher m = CommandParser.ADD_DEADLINE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 2) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        String desc = m.group(1);
        LocalDateTime dateTime = LocalDateTime.parse(m.group(2), CommandParser.DATETIME_FORMATTER);
        return new AddDeadlineCommand(desc, dateTime);
    }

    private static AddEventCommand getAddEventCommand(String input) throws QuackersException {
        Matcher m = CommandParser.ADD_EVENT_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 2) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        String desc = m.group(1);
        LocalDateTime dateTime = LocalDateTime.parse(m.group(2), CommandParser.DATETIME_FORMATTER);
        return new AddEventCommand(desc, dateTime);
    }

    private static DeleteTaskCommand getDeleteTaskCommand(String input) throws QuackersException {
        Matcher m = CommandParser.DELETE_TASK_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        int index = Integer.parseInt(m.group(1));
        return new DeleteTaskCommand(index);
    }

    private static MarkTaskAsDoneCommand getMarkTaskAsDoneCommand(String input) throws QuackersException {
        Matcher m = CommandParser.MARK_TASK_AS_DONE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        int index = Integer.parseInt(m.group(1));
        return new MarkTaskAsDoneCommand(index);
    }

    private static MarkTaskAsUndoneCommand getMarkTaskAsUndoneCommand(String input) throws QuackersException {
        Matcher m = CommandParser.MARK_TASK_AS_UNDONE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getInvalidCommand());
        }

        int index = Integer.parseInt(m.group(1));
        return new MarkTaskAsUndoneCommand(index);
    }
}
