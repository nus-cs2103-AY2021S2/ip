/**
 * Represents tasks the user wants to keep track of.
 */
public class Task {
    protected String description;
    protected String moreInfo;
    protected boolean isDone;

    /**
     * Constructor for this Task object.
     *
     * @param   description  Task Description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks and returns the status of the task.
     *
     * @return task status.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Sets the task isDone status to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts information about the task to be saved to hard disk.
     *
     * @return string containing information about the task.
     */
    public String save() {
        return toString();
    }

    /**
     * Returns a string representation of the Task.
     * @return task completion status and description.
     */
    @Override
    public String toString() {
        if (this.moreInfo != null) {
            return this.getStatusIcon() + "[i] " + this.description;
        } else {
            return this.getStatusIcon() + "[ ] " + this.description;
        }
    }
}
