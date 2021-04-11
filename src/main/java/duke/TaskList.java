package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;

import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;

/**
 * Abstraction to store Tasks in a list.
 */
public class TaskList {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Converts the tasks to string (to be used by Storage for file saving).
     *
     * @param task task
     * @return string representation of task
     */
    public static String taskToString(Task task) {
        StringBuilder builder = new StringBuilder();
        if (task instanceof Deadline) {
            builder.append('D');
        } else if (task instanceof Event) {
            builder.append('E');
        } else if (task instanceof Todo) {
            builder.append('T');
        }

        builder.append("|");
        builder.append(task.getTaskDone() ? 1 : 0);

        builder.append("|");
        builder.append(task.getTaskName());

        if (task instanceof Deadline) {
            builder.append("|");
            builder.append(DATE_TIME_FORMATTER.format(((Deadline) task).getDeadline()));
        } else if (task instanceof Event) {
            builder.append("|");
            builder.append(DATE_TIME_FORMATTER.format(((Event) task).getDate()));
        }

        return builder.toString();
    }

    /**
     * Parses the given string (from file) into the correct task object.
     *
     * @param taskString task string (from file)
     * @return task object
     */
    public static Task parseTask(String taskString) {
        String[] taskDetails = taskString.split("\\|");
        String type = taskDetails[0];
        Task task = null;

        if (type.equals("T")) {
            task = new Todo(taskDetails[2]);
        } else if (type.equals("D")) {
            task = new Deadline(taskDetails[2], LocalDateTime.parse(taskDetails[3], DATE_TIME_FORMATTER));
        } else if (type.equals("E")) {
            task = new Event(taskDetails[2], LocalDateTime.parse(taskDetails[3], DATE_TIME_FORMATTER));
        }

        if (taskDetails[1].equals("1") && task != null) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Adds a new task into the list
     *
     * @param t task to be added
     */
    public void addTask(Task t) {
        assert (t != null);
        this.tasks.add(t);
    }

    public Task getTask(int index) {
        assert (index < this.tasks.size());
        assert (index >= 0);
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public Stream<Task> toStream() {
        return tasks.stream();
    }
}
