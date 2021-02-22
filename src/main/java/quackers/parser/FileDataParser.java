package quackers.parser;

import quackers.QuackersException;
import quackers.ui.Ui;
import quackers.task.Deadline;
import quackers.task.Event;
import quackers.task.Task;
import quackers.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the storage parser.
 */
public class FileDataParser {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Pattern COMMAND_TYPE_PATTERN = Pattern.compile("^\\[(T|D|E)\\]");
    private static final Pattern TODO_DATA_PATTERN = Pattern.compile("\\[(T)\\]\\[(\\*|\\s)\\] ([a-zA-Z0-9\\s]+)");
    private static final Pattern DEADLINE_DATA_PATTERN = Pattern.compile("\\[(D)\\]\\[(\\*|\\s)\\] ([a-zA-Z0-9\\s]+) \\(by: (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})\\)");
    private static final Pattern EVENT_DATA_PATTERN = Pattern.compile("\\[(E)\\]\\[(\\*|\\s)\\] ([a-zA-Z0-9\\s]+) \\(at: (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})\\)");

    /**
     * Parses a list of raw text lines and generate a TaskList from it.
     *
     * @param lines List of raw task data.
     * @return TaskList object.
     * @throws QuackersException If invalid task data specified.
     */
    public static ArrayList<Task> getTaskListData(List<String> lines) throws QuackersException {
        ArrayList<Task> taskList = new ArrayList<>();

        for (String line : lines) {
            String commandType = FileDataParser.getCommandType(line);
            switch (commandType) {
            case "T":
                taskList.add(FileDataParser.getTodoData(line));
                break;
            case "D":
                taskList.add(FileDataParser.getDeadlineData(line));
                break;
            case "E":
                taskList.add(FileDataParser.getEventData(line));
                break;
            default:
                throw new QuackersException(Ui.getLoadTaskListFailure());
            }
        }
        return taskList;
    }

    /**
     * Retrieves the command type from the text input.
     *
     * @param input Raw task data.
     * @return Command type.
     * @throws QuackersException If there are no supported command types found.
     */
    private static String getCommandType(String input) throws QuackersException {
        Matcher m = FileDataParser.COMMAND_TYPE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new QuackersException(Ui.getLoadTaskListFailure());
        }

        String commandType = m.group(1);
        return commandType;
    }

    /**
     * Retrieves the Todo object from the text input.
     *
     * @param input Raw task data.
     * @return Todo object.
     * @throws QuackersException If invalid task data specified.
     */
    private static Todo getTodoData(String input) throws QuackersException {
        Matcher m = FileDataParser.TODO_DATA_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 3) {
            throw new QuackersException(Ui.getLoadTaskListFailure());
        }

        Todo todo = new Todo(m.group(3));
        if (m.group(2).equals("*")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Retrieves the Deadline object from the text input.
     *
     * @param input Raw task data.
     * @return Deadline object.
     * @throws QuackersException If invalid task data specified.
     */
    private static Deadline getDeadlineData(String input) throws QuackersException {
        Matcher m = FileDataParser.DEADLINE_DATA_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 4) {
            throw new QuackersException(Ui.getLoadTaskListFailure());
        }

        Deadline deadline = new Deadline(m.group(3), LocalDateTime.parse(m.group(4), DATETIME_FORMATTER));
        if (m.group(2).equals("*")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Retrieves the Event object from the text input.
     *
     * @param input Raw task data.
     * @return Event object.
     * @throws QuackersException If invalid task data specified.
     */
    private static Event getEventData(String input) throws QuackersException {
        Matcher m = FileDataParser.EVENT_DATA_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 4) {
            throw new QuackersException(Ui.getLoadTaskListFailure());
        }

        Event event = new Event(m.group(3), LocalDateTime.parse(m.group(4), DATETIME_FORMATTER));
        if (m.group(2).equals("*")) {
            event.markAsDone();
        }
        return event;
    }
}
