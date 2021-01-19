package duke.main;

/**
 * Parent class for all tasks in Duke
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String tick if the current task is done, otherwise, " ".
     * @return a String tick if the task is done, otherwise, " ".
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Updates the isDone status of the current task to be true.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
