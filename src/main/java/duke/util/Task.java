package duke.util;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a Task.
 * Includes description of the task and an indicator of whether it is completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    /**
     * Constructor to create a Task with a description.
     * 
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = null;
    }

    /**
     * Constructor to create a Task with a description and a date.
     * 
     * @param description Description of the task.
     * @param date Date of the task.
     */
    public Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    /**
     * Set task as completed.
     */
    public void markComplete() {
        isDone = true;
    }

    private String getStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns description of task.
     * 
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns String in the form "[status] description".
     * 
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), description);
    }

    /**
     * Export data into a standardised format.
     * 
     * @return List of task details.
     */
    protected abstract List<String> exportData();
}
