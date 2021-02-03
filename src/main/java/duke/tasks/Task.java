package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Abstract class to be extended from, for all tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" d.MMM.yyyy HH:mm");

    /**
     * Constructor to be shared by extending classes.
     * @param description string content of task.
     * @param type string type of task.
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks task as done.
     * @return task marked done.
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Checks if task is done.
     * @return boolean value of task being done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a status icon, depicting whether task is done.
     * @return "X" if done, " " if undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    /**
     * Returns type of task.
     * @return type of task.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns description of task.
     * @return description of task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
