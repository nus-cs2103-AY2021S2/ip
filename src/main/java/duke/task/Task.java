package duke.task;

import java.time.LocalDate;

/**
 * Represents a task that has to be done.
 */
public class Task {

    protected String description;
    protected boolean isCompleted;

    /**
     * constructor of task.
     *
     * @param description description of task.
     * @param isCompleted boolean to specify if the task has been completed.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    /**
     * Returns formatted string of the task details to store in harddisk file.
     * @return Formatted string.
     */
    public String getFormattedData() {
        String status = isCompleted ? "1" : "0";
        return status + " | " + description;
    }

    public LocalDate getTaskDate() {
        return null;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns description and status of the task.
     * @return String.
     */
    @Override
    public String toString() {
        String status = isCompleted ? "X" : "";
        return "[" + status + "] " + description;
    }
}
