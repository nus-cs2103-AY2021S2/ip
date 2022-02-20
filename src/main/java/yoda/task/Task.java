package yoda.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract task class to handle task-related information.
 */
public abstract class Task implements Serializable {
    /** Description of the task created */
    protected String description;
    /** Shows if the task has been completed */
    protected boolean isDone;
    /** Date and time related to the task created */
    protected LocalDateTime dateTime;
    /** Shows if the task has been marked to be deleted */
    protected boolean isMarkedToBeDeleted;

    /**
     * Creates a task object.
     * @param description Description of task object.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        isMarkedToBeDeleted = false;
    }

    /**
     * Formats the date and time to make it more readable for the user.
     * @return Formatted date and time.
     */
    public String formatDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Sets the date and time associated with the task.
     * @param dateTime The date and time associated with the task.
     */
    public void setDateTime(String dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.dateTime = LocalDateTime.parse(dateTime, format);
    }

    /**
     * Checks if the task has been done.
     * @return Status of the task.
     */
    public String checkStatus() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task to be deleted.
     */
    public void markToBeDeleted() {
        isMarkedToBeDeleted = true;
    }

    /**
     * Formats the Task information to be user-readable.
     * @return User-readable format of the Task object.
     */
    @Override
    public String toString() {
        return "[" + checkStatus() + "] " + description;
    }
}
