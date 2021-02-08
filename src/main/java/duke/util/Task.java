package duke.util;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a Task.
 * Includes description of the task and an indicator of whether it is completed.
 */
public abstract class Task implements Comparable<Task> {
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
     * Returns a new task marked as done.
     */
    public abstract Task markDone();

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
     * Returns date of task.
     *
     * @return Date of task.
     */
    public LocalDate getDate() {
        return date;
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

    /**
     * Compares 2 task.
     *     First, completed task is smaller.
     *     Second, absence of date (eg. todo) is smaller.
     *     Third, eariler date is smaller
     *     Last, by description lexicographically.
     *
     * @param other The task to be compared to.
     * @return -1 if this task is smaller, 0 if same, 1 if other task is smaller.
     */
    @Override
    public int compareTo(Task other) {
        if (!(isDone ^ other.isDone)) {
            return compareDate(other);
        } else if (isDone) {
            return 1;
        } else {
            return -1;
        }
    }

    private int compareDate(Task other) {
        if (date == null && other.date == null) {
            return description.compareTo(other.description);
        } else if (date == null) {
            return -1;
        } else if (other.date == null) {
            return 1;
        } else if (date.equals(other.date)) {
            return description.compareTo(other.description);
        } else {
            return date.compareTo(other.date);
        }
    }
}
