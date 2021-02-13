package duke.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.message.Messages;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TaskListDecoder {
    private static final Pattern ENCODED_TASK_FORMAT =
            Pattern.compile("(?<encodedTaskType>[A-Z]{1})/(?<encodedTaskData>.*)");
    private static final Pattern ENCODED_TODO_DATA_FORMAT =
            Pattern.compile("(?<status>[0-1]{1})/(?<description>[^/]+)");
    private static final Pattern ENCODED_DEADLINE_DATA_FORMAT =
            Pattern.compile("(?<status>[0-1]{1})/(?<description>[^/]+)/(?<dateTime>[^/]+)");
    private static final Pattern ENCODED_EVENT_DATA_FORMAT =
            Pattern.compile("(?<status>[0-1]{1})/(?<description>[^/]+)/(?<date>[^/]+)");
    private static final String DONE_STATUS = "1";

    public static TaskList decodeTaskList(List<String> encodedTaskList) throws DukeException {
        List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);
    }

    private static Task decodeTaskFromString(String encodedTask) throws DukeException {
        final Matcher matcher = ENCODED_TASK_FORMAT.matcher(encodedTask.trim());

        if (!matcher.matches()) {
            throw new DukeException(String.format(encodedTask, Messages.MESSAGE_INVALID_ENCODED_FORMAT));
        }

        String encodedTaskType = matcher.group("encodedTaskType");
        String encodedTaskData = matcher.group("encodedTaskData");
        Task decodedTask = null;

        switch(encodedTaskType) {
        case(Todo.ENCODED_TYPE):
            decodedTask = decodeTodo(encodedTaskData);
            break;
        case(Deadline.ENCODED_TYPE):
            decodedTask = decodeDeadline(encodedTaskData);
            break;
        case(Event.ENCODED_TYPE):
            decodedTask = decodeEvent(encodedTaskData);
            break;
        default:
            break;
        }
        return decodedTask;
    }

    private static boolean decodeStatus(String status) {
        return status.equals(DONE_STATUS);
    }

    private static Todo decodeTodo(String encodedTodoData) throws DukeException {
        final Matcher matcher = ENCODED_TODO_DATA_FORMAT.matcher(encodedTodoData.trim());
        if (!matcher.matches()) {
            throw new DukeException(String.format(encodedTodoData, Messages.MESSAGE_INVALID_ENCODED_TODO_FORMAT));
        }

        String status = matcher.group("status");
        String description = matcher.group("description");
        return new Todo(decodeStatus(status), description);
    }

    private static Deadline decodeDeadline(String encodedDeadlineData) throws DukeException {
        final Matcher matcher = ENCODED_DEADLINE_DATA_FORMAT.matcher(encodedDeadlineData.trim());
        if (!matcher.matches()) {
            throw new DukeException(String.format(encodedDeadlineData,
                    Messages.MESSAGE_INVALID_ENCODED_DEADLINE_FORMAT));
        }

        String status = matcher.group("status");
        String description = matcher.group("description");
        String dateTime = matcher.group("dateTime");
        return new Deadline(decodeStatus(status), description, dateTime);
    }

    private static Event decodeEvent(String encodedEventData) throws DukeException {
        final Matcher matcher = ENCODED_EVENT_DATA_FORMAT.matcher(encodedEventData.trim());
        if (!matcher.matches()) {
            throw new DukeException(String.format(encodedEventData,
                    Messages.MESSAGE_INVALID_ENCODED_EVENT_FORMAT));
        }

        String status = matcher.group("status");
        String description = matcher.group("description");
        String date = matcher.group("date");
        return new Event(decodeStatus(status), description, date);
    }
}
