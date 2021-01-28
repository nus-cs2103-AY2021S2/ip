package duke;

import duke.models.Deadline;
import duke.models.Event;
import duke.models.Task;
import duke.models.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Abstraction to store Tasks in a list.
 */
public class TaskList {
    public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Converts the tasks to string (to be used by Storage for file saving).
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
     * @param taskString task string (from file)
     * @return task object
     */
    public static Task parseTask(String taskString) {
        String[] taskStringArray = taskString.split("\\|");
        String type = taskStringArray[0];
        Task task = null;

        if (type.equals("T")) {
            task = new Todo(taskStringArray[2]);
        } else if (type.equals("D")) {
            task = new Deadline(taskStringArray[2], LocalDateTime.parse(taskStringArray[3], DATE_TIME_FORMATTER));
        } else if (type.equals("E")) {
            task = new Event(taskStringArray[2], LocalDateTime.parse(taskStringArray[3], DATE_TIME_FORMATTER));
        }

        if (taskStringArray[1].equals("1") && task != null) {
            task.markAsDone();
        }

        return task;
    }
}
