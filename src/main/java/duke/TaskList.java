package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class TaskList represents a taskList and provides operations to interact with that taskList.
 * Operations provided: addTask, doneTask, deleteTask.
 */
class TaskList {
    private final ArrayList<Task> listUsed;

    /**
     * Returns a TaskList.
     */
    public TaskList() {
        this.listUsed = new ArrayList<>();
    }

    /**
     * Adds a task with specified description to TaskList.
     *
     * @param taskDescription The description of task.
     * @return The task that have been added.
     */
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
        } else if (!(taskDescription.contains("/at") || taskDescription.contains("/by"))) {
            task = new ToDo(taskDescription);
        } else {
            task = null;
        }
        assert task != null : "Must be a type among those 3 types.";
        this.listUsed.add(task);
        return task;
    }

    /**
     * Marks a task in TaskList as (Done).
     *
     * @param index Index of the done task in TaskList.
     * @return the Task that marked as (Done).
     */
    public Task doneTask(int index) {
        Task task = this.listUsed.get(index - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a task from TaskList.
     *
     * @param index Index of the deleted task in TaskList.
     */
    public void deleteTask(int index) {
        this.listUsed.remove(index - 1);
    }

    public ArrayList<Task> getListUsed() {
        return listUsed;
    }
}
