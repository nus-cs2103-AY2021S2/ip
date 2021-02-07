package duke.tasks;

/**
 * Parent class for all tasks in Duke
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class objects.
     * @param description description for the task created
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String tick if the current task is done, otherwise, " ".
     * @return a String tick if the task is done, otherwise, " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Updates the isDone status of the current task to be true.
     * @return if isDone is currently false, set it to true and return true;
     *              otherwise, return false;
     */
    public boolean markAsDone() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

    /**
     * Acts as a helper method to print out the details of the task
     * @return the details of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Helper method to get the details of the task to be stored
     * @returnthe the details of the task to be stored
     */
    public String infoToStore() {
        String divider = " | ";
        return (isDone ? "1" : "0") + divider
                + description;
    }
}
