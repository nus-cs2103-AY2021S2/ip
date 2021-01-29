package seedu.task;

// adapted from partial solution on the module webpage

/**
 * Represents a task in the application.
 */
public class Task {
    /** The task description. */
    protected String description;
    /** The task status. */
    protected boolean isDone;

    /**
     * Creates an instance of {@code Task} with empty task list.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns {@code String} representation of this task's status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns {@code int} representation of this task's status.
     * Note that it returns {@code 1} if this task is "not done".
     */
    public int convertNotDoneStatusToOne() {
        return isDone ? 0 : 1;
    }

    /**
     * Changes this task's status to "done".
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns {@code String} representation of this {@code Task}.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

