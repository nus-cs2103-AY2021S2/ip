import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
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
            builder.append(((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
            builder.append("|");
            builder.append(((Event) task).getDate());
        }

        return builder.toString();
    }

    public static Task parseTask(String taskString) {
        String[] taskStringArray = taskString.split("\\|");
        String type = taskStringArray[0];
        Task task = null;

        if (type.equals("T")) {
            task = new Todo(taskStringArray[2]);
        } else if (type.equals("D")) {
            task = new Deadline(taskStringArray[2], LocalDateTime.parse(taskStringArray[3]));
        } else if (type.equals("E")) {
            task = new Event(taskStringArray[2], LocalDateTime.parse(taskStringArray[3]));
        }

        if (taskStringArray[1].equals('1') && task != null) {
            task.markAsDone();
        }

        return task;
    }
}
