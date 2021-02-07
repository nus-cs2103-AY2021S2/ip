package duke.task;

/**
 * The Task class represents the abstraction of a
 * task which can have a description, and be marked done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with a description
     * that is initially marked as not done.
     * @param description The specified description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Setter to update the done status of a task.
     * @param isDone The new done status.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Getter to check the done status of a task.
     * @return The done status of a task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns task description.
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns an icon based on the done status of a task.
     * @return The icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Converts a task to the format to be saved to a file.
     * @return The task in save format.
     */
    public String toSaveFormat() {
        throw new UnsupportedOperationException("Method must be implemented by child classes");
    };

    /**
     * Converts a task to the format to be displayed to the user by the Ui.
     * @return The task in display format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
