package duke.task;

/**
 * Represents a generic task.
 *
 * @author Benedict Khoo
 */
public abstract class Task {
    protected static final String DONE_ICON = "\u2713";
    protected static final String NOT_DONE_ICON = " ";

    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructs a Task with the given description and type.
     *
     * @param description The task description.
     * @param type        The task type.
     */
    protected Task(String description, String type) {
        this(description, type, false);
    }

    /**
     * Constructs a Task with the given description and type.
     *
     * @param description The task description.
     * @param type        The task type.
     * @param isDone      The done status.
     */
    protected Task(String description, String type, boolean isDone) {
        assert description != null : "description must not be null!";
        assert type != null : "type must not be null!";

        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a status icon indicating the done status of this task.
     *
     * @return A status icon indicating the done status of this task.
     */
    public String getStatusIcon() {
        if (isDone) {
            return DONE_ICON;
        } else {
            return NOT_DONE_ICON;
        }
    }

    /**
     * Returns the type of this task.
     *
     * @return The type of this task.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the done status of this task to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a serialized string representing this task.
     *
     * @return A serialized string representing this task.
     */
    public abstract String serialize();

    /**
     * Returns a string representation of this task suitable for display to the user.
     *
     * @return A user-friendly representation of this task.
     */
    public abstract String toString();
}
