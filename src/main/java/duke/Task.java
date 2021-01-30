package duke;

/**
 * Represents a task. A <code>Task</code> object corresponds to a task
 * description supplied by the user. eg., <code>running</code>
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method is used to return the status icon of the task.
     * If the task is done, ✓ is returned. Else, ✘ is returned.
     * @return String representation for the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * This method is used to return the Task object after setting isDone to true.
     * @return Current instance of Task object.
     */
    public Task markAsDone() {
        isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " | " + description;
    }
}
