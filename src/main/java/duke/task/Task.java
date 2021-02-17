package duke.task;

/**
 * Class representing a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the Task class
     *
     * @param description A brief description of the task.
     * @param isDone "0" if the task is done. "1" if the task is not done.
     */
    protected Task(String description, String isDone) {
        this.description = description;
        this.isDone = isDone.equals("1") ? true : false;
    }

    /**
     * Displays whether a task is done.
     *
     * @return A cross symbol if task is not done, a tick symbol if task is done.
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the description of the task.
     *
     * @return A String describing the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task, whether it is done or not.
     *
     * @return True if the task is done, false if task is not done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Updates the task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Updates the task as incomplete.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status and the description of the task.
     *
     * @return A tick/cross symbol and the description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
