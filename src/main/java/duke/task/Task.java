package duke.task;

/**
 * Represents a task. A base class for other types of tasks to inherit from.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor takes in the description of the task.
     * A task by default is not done.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Setter to update whether a task is done.
     * @param isDone True if the task is done and false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Getter to check whether a task is done.
     * @return True if the task is done and false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        assert description != null;
        return description;
    }

    /**
     * Returns a string-based representation of an icon of whether a task is done.
     * @return A tick icon if the task is done, otherwise a cross icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Checks if a task is the same as the task to check with.
     * Method must be implemented by subclasses.
     * @param toCheck The task to check with
     * @return True if the tasks are the same, false otherwise.
     */
    public boolean isSameTask(Task toCheck) {
        throw new UnsupportedOperationException("Method must be implemented by child classes");
    }

    /**
     * Encodes a task into a decodable string representation of the task.
     * @return The encoded string representation of the task.
     */
    public String encode() {
        throw new UnsupportedOperationException("Method must be implemented by child classes");
    }

    /**
     * Converts a task to the format to be displayed to the user by the Ui.
     * @return The task in display format.
     */
    @Override
    public String toString() {
        assert description != null;
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
