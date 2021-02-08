package owen.task;

/**
 * An abstract task with a description and a status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task() {
        this.description = "";
        this.isDone = false;
    }

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done and return copy with updated state.
     *
     * @return Copy of task with the state updated to done.
     */
    public abstract Task markAsDone();

    /**
     * Returns true if task is due within specified days.
     *
     * @param days Days to check if task is due by.
     * @return Whether the task is due soon.
     */
    public abstract boolean isDue(long days);

    /**
     * Serializes task into a String.
     *
     * @return Serialized version of task as string.
     */
    public abstract String serialize();

    protected abstract String getTypeIcon();

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
