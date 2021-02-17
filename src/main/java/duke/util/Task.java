package duke.util;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a Task.
 * <br>- Has a description
 * <br>- Has an optional date
 * <br>- Has an indicator of whether it is completed.
 * <br>- Has an indicator of its priority.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected LocalDate date;
    protected boolean isDone;
    protected boolean isHighPriority;

    /**
     * Constructor to create a Task with a description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = null;
        this.isHighPriority = false;
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
        this.isHighPriority = false;
    }

    /**
     * Returns the task marked as done.
     *
     * @return Done task.
     */
    public abstract Task markDone();

    /**
     * Returns the task as high priority;
     *
     * @return High priority task.
     */
    public abstract Task setHighPriority();

    /**
     * Returns the task as low priority;
     *
     * @return Low priority task.
     */
    public abstract Task setLowPriority();

    /**
     * Export data into a standardised format.
     *
     * @return List of task details.
     */
    protected abstract List<String> exportData();

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
     * Returns priority of task.
     *
     * @return True if high priority else false.
     */
    public boolean isHighPriority() {
        return isHighPriority;
    }

    /**
     * Returns String in the form "[status] description".
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s%s",
                getStatus(),
                isHighPriority ? "IMPT! " : "",
                description);
    }

    private String getStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Compares 2 task.
     *     First, high priority task is smaller.
     *     Second, completed task is smaller.
     *     Third, absence of date (eg. todo) is smaller.
     *     Forth, eariler date is smaller
     *     Last, by description lexicographically.
     *
     * @param other The task to be compared to.
     * @return -1 if this task is smaller, 0 if same, 1 if this task is bigger.
     */
    @Override
    public int compareTo(Task other) {
        return comparePriority(other);
    }

    private int comparePriority(Task other) {
        if (!(isHighPriority ^ other.isHighPriority)) {
            return compareCompleted(other);
        } else if (isHighPriority) {
            return -1;
        } else {
            return 1;
        }
    }

    private int compareCompleted(Task other) {
        if (!(isDone ^ other.isDone)) {
            return compareDateExist(other);
        } else if (isDone) {
            return 1;
        } else {
            return -1;
        }
    }

    private int compareDateExist(Task other) {
        if (date == null && other.date == null) {
            return compareDescription(other);
        } else if (date == null) {
            return -1;
        } else if (other.date == null) {
            return 1;
        } else {
            return compareDate(other);
        }
    }

    private int compareDate(Task other) {
        if (date.equals(other.date)) {
            return compareDescription(other);
        }

        return date.compareTo(other.date);
    }

    private int compareDescription(Task other) {
        return description.compareTo(other.description);
    }
}
