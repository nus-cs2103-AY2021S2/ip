package project;

/**
 * Represents a task.
 */
public class Task {
    /** Description of the task. */
    protected String description;
    /** Status of the task. */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets status of task to "done".
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (getStatusIcon() + " " + getDescription());
    }
}
