package duke.parser;

import duke.DukeException;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileDataParser {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Pattern COMMAND_TYPE_PATTERN = Pattern.compile("^\\[(T|D|E)\\]");
    private static final Pattern TODO_DATA_PATTERN = Pattern.compile("\\[(T)\\]\\[(\\*|\\s)\\] ([a-zA-Z0-9\\s]+)");
    private static final Pattern DEADLINE_DATA_PATTERN = Pattern.compile("\\[(D)\\]\\[(\\*|\\s)\\] ([a-zA-Z0-9\\s]+) \\(by: (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})\\)");
    private static final Pattern EVENT_DATA_PATTERN = Pattern.compile("\\[(E)\\]\\[(\\*|\\s)\\] ([a-zA-Z0-9\\s]+) \\(at: (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})\\)");

    public static ArrayList<Task> getTaskListData(List<String> lines) throws DukeException {
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
                throw new DukeException(Ui.getLoadTaskListFailure());
            }
        }
        return taskList;
    }

    public static String getCommandType(String input) throws DukeException {
        Matcher m = FileDataParser.COMMAND_TYPE_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 1) {
            throw new DukeException(Ui.getLoadTaskListFailure());
        }

        String commandType = m.group(1);
        return commandType;
    }

    public static Todo getTodoData(String input) throws DukeException {
        Matcher m = FileDataParser.TODO_DATA_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 3) {
            throw new DukeException(Ui.getLoadTaskListFailure());
        }

        Todo todo = new Todo(m.group(3));
        if (m.group(2).equals("*")) {
            todo.markAsDone();
        }
        return todo;
    }

    public static Deadline getDeadlineData(String input) throws DukeException {
        Matcher m = FileDataParser.DEADLINE_DATA_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 4) {
            throw new DukeException(Ui.getLoadTaskListFailure());
        }

        Deadline deadline = new Deadline(m.group(3), LocalDateTime.parse(m.group(4), DATETIME_FORMATTER));
        if (m.group(2).equals("*")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    public static Event getEventData(String input) throws DukeException {
        Matcher m = FileDataParser.EVENT_DATA_PATTERN.matcher(input);
        if (!m.find() || m.groupCount() != 4) {
            throw new DukeException(Ui.getLoadTaskListFailure());
        }

        Event event = new Event(m.group(3), LocalDateTime.parse(m.group(4), DATETIME_FORMATTER));
        if (m.group(2).equals("*")) {
            event.markAsDone();
        }
        return event;
    }
}
