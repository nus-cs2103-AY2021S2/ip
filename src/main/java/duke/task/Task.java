package duke.task;

/**
 * An abstract base class of addable task.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task, specifying the description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for task, specifying the task's status and description.
     *
     * @param done integer value to indicate the task's status.
     * @param description description of the task
     */
    public Task(int done, String description) {
        this.isDone = done == 1 ? true : false;
        this.description = description;
    }

    /**
     * Get the status icon in regards to the task's status.
     *
     * @return "X" symbol if it is done, else returns an empty string " ".
     */
    public String getStatusIcon() {
        return isDone ? "\u2718" : " ";
    }

    public void setDone() {
        isDone = true;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Formats the task's information into a formatted string that is suitable for storing.
     */
    public abstract String toStorageString();
}
