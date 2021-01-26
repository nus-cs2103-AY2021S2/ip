package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    public  ArrayList<Task> listUsed;

    public TaskList() {
        this.listUsed = new ArrayList<>();
    }

    public Task addTask(String taskDescription) {
        Task task;
        if (taskDescription.contains("/at")) {
            String taskName = taskDescription.substring(0, taskDescription.indexOf("/at"));
            String dateTime = taskDescription.substring(taskDescription.indexOf("/at") + 4);
            LocalDateTime eventTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            task = new Event(taskName, eventTime);
        } else if (taskDescription.contains("/by")) {
            String taskName = taskDescription.substring(0, taskDescription.indexOf("/by"));
            String dateTime = taskDescription.substring(taskDescription.indexOf("/by") + 4);
            LocalDateTime dlTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            task = new Deadline(taskName, dlTime);
        } else {
            task = new ToDo(taskDescription);
        }
        this.listUsed.add(task);
        return task;
    }

    public Task doneTask(int index) {
        Task task = this.listUsed.get(index - 1);
        task.markAsDone();
        return task;
    }

    public void deleteTask(int index) {
        this.listUsed.remove(index - 1);
    }
}
