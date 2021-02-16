package duke;

import duke.command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the parser used by the Duke chat bot.
 * It parses raw user inputs, and convert them into
 * a suitable Command format for execution.
 */
public class Parser {

    private static final String INVALID_COMMAND = "I'm not trained with these commands yet...";
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final Pattern COMMAND_TYPE_PATTERN = Pattern.compile("usage|bye|list|find|save|todo|deadline|event|delete|done");
    private static final Pattern USAGE_PATTERN = Pattern.compile("usage");
    private static final Pattern BYE_PATTERN = Pattern.compile("bye");
    private static final Pattern LIST_TASKS_PATTERN = Pattern.compile("list");
    private static final Pattern FIND_TASKS_PATTERN = Pattern.compile("find ([a-zA-Z0-9\\s]+)");
    private static final Pattern SAVE_TASKS_PATTERN = Pattern.compile("save");
    private static final Pattern ADD_TODO_PATTERN = Pattern.compile("todo ([a-zA-Z0-9\\s]+)");
    private static final Pattern ADD_DEADLINE_PATTERN = Pattern.compile("deadline ([a-zA-Z0-9\\s]+) /by (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
    private static final Pattern ADD_EVENT_PATTERN = Pattern.compile("event ([a-zA-Z0-9\\s]+) /at (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
    private static final Pattern DELETE_TASK_PATTERN = Pattern.compile("delete ([0-9]+)");
    private static final Pattern MARK_TASK_AS_DONE_PATTERN = Pattern.compile("done ([0-9]+)");

    public static String getCommandType(String input) throws DukeException {
        String commandType = input.split(" ")[0].toLowerCase();
        Matcher m = Parser.COMMAND_TYPE_PATTERN.matcher(commandType);
        if (!m.find()) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }
        return commandType;
    }

    public static UsageCommand getUsageCommand(String input) throws DukeException {
        Matcher m = Parser.USAGE_PATTERN.matcher(input);
        if (!m.find()) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }
        return new UsageCommand();
    }

    public static ByeCommand getByeCommand(String input) throws DukeException {
        Matcher m = Parser.BYE_PATTERN.matcher(input);
        if (!m.find()) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }
        return new ByeCommand();
    }

    public static ListTasksCommand getListTasksCommand(String input) throws DukeException {
        Matcher m = Parser.LIST_TASKS_PATTERN.matcher(input);
        if (!m.find()) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }
        return new ListTasksCommand();
    }

    public static FindTasksCommand getFindTasksCommand(String input) throws DukeException {
        Matcher m = Parser.FIND_TASKS_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }

        String keyword = m.group(1);
        return new FindTasksCommand(keyword);
    }


    public static SaveTasksCommand getSaveTasksCommand(String input) throws DukeException {
        Matcher m = Parser.SAVE_TASKS_PATTERN.matcher(input);
        if (!m.find()) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }
        return new SaveTasksCommand();
    }


    public static AddTodoCommand getAddTodoCommand(String input) throws DukeException {
        Matcher m = Parser.ADD_TODO_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }

        String desc = m.group(1);
        return new AddTodoCommand(desc);
    }

    public static AddDeadlineCommand getAddDeadlineCommand(String input) throws DukeException {
        Matcher m = Parser.ADD_DEADLINE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 2) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }

        String desc = m.group(1);
        LocalDateTime dateTime = LocalDateTime.parse(m.group(2), Parser.DATETIME_FORMATTER);
        return new AddDeadlineCommand(desc, dateTime);
    }

    public static AddEventCommand getAddEventCommand(String input) throws DukeException {
        Matcher m = Parser.ADD_EVENT_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 2) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }

        String desc = m.group(1);
        LocalDateTime dateTime = LocalDateTime.parse(m.group(2), Parser.DATETIME_FORMATTER);
        return new AddEventCommand(desc, dateTime);
    }

    public static DeleteTaskCommand getDeleteTaskCommand(String input) throws DukeException {
        Matcher m = Parser.DELETE_TASK_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }

        Integer index = Integer.parseInt(m.group(1));
        return new DeleteTaskCommand(index);
    }


    public static MarkTaskAsDoneCommand getMarkTaskAsDoneCommand(String input) throws DukeException {
        Matcher m = Parser.MARK_TASK_AS_DONE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new DukeException(Parser.INVALID_COMMAND);
        }

        Integer index = Integer.parseInt(m.group(1));
        return new MarkTaskAsDoneCommand(index);
    }

    /**
     * Processes the raw user input and converts it
     * into a friendly Command data type for the
     * Duke chat bot to execute.
     *
     * @param input Raw user input.
     * @return User input in a Command format.
     * @throws DukeException If command arguments are misused.
     */
    public static Command parseCommand(String input) throws DukeException {
        String commandType = Parser.getCommandType(input);

        switch (commandType) {
            case "usage":
                return Parser.getUsageCommand(input);
            case "bye":
                return Parser.getByeCommand(input);
            case "list":
                return Parser.getListTasksCommand(input);
            case "find":
                return Parser.getFindTasksCommand(input);
            case "save":
                return Parser.getSaveTasksCommand(input);
            case "todo":
                return Parser.getAddTodoCommand(input);
            case "deadline":
                return Parser.getAddDeadlineCommand(input);
            case "event":
                return Parser.getAddEventCommand(input);
            case "delete":
                return Parser.getDeleteTaskCommand(input);
            case "done":
                return Parser.getMarkTaskAsDoneCommand(input);
            default:
                throw new DukeException(Parser.INVALID_COMMAND);
        }
    }
}

