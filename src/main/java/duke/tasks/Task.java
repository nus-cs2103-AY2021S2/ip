package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Models a task with a description and a boolean indicating if task is completed. Parent class of Deadline, Event
 * and ToDo classes.
 */
public abstract class Task {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("[d/M/yyyy HHmm][d MMM yy HHmm]"
            + "[dd-MM-yy HHmm]");

    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of a Task.
     *
     * @return String description of a task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks current task as done.
     */
    public void markAsDone() {
        assert !this.isDone;
        this.isDone = true;
    }

    /**
     * Checks if this Task is marked as done.
     *
     * @return boolean indicating if this Task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns String description of this Task item with status indicating if it is marked as done.
     *
     * @return String description of this Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
