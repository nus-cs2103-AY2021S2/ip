package project.task;

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
        assert description.length() > 0;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns {@code String} representation of this task's status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Changes this task's status to "done".
     */
    public void markAsDone() {
        this.isDone = true;
        assert this.getStatusIcon().equals("X");
    }

    /**
     * Returns {@code String} representation of this {@code Task}.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

