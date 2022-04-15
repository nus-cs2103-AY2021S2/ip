/**
 * Represents a Deadline Task.
 * @author Arihant Jain
 */
public class Task {
    /**
     * The Task Description.
     */
    protected String description;
    /**
     * The boolean indicating if Task is completed.
     */
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the task description
     */
    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters"})
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Gets completion status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    /**
     * Mark Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /***
     * Get list variation Task String.
     * @return String Task String
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
