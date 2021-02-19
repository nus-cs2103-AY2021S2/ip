package task;

/**
 * Represents a task
 */
public class Task {
    protected String description;
    private boolean isDone;

    /**
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the task's doneness to a boolean
     * @param b
     */
    public void setDone(boolean b) {
        isDone = b;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return (getStatusIcon() + " " + description);
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }
}
