package rick;

import rick.exceptions.TaskAlreadyDoneException;

/**
 * Represents a task. A <code>Task</code> object corresponds
 * to a task description supplied by the user. eg., <code>running</code>
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * This method is used to return the status icon of the task.
     * If the task is done, ✓ is returned. Else, ✘ is returned.
     *
     * @return String representation for the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * This method is used to return the <code>Task</code> object
     * after setting isDone to true.
     *
     * @return Current instance of <code>Task</code> object.
     */
    public Task markAsDone() throws TaskAlreadyDoneException {
        if(isDone) {
            throw new TaskAlreadyDoneException();
        }
        assert isDone : "Task is not marked as done yet";
        isDone = true;
        return this;
    }

    /**
     * This method is used to return the description of
     * the <code>Task</code>.
     *
     * @return Description of the current instance of
     * <code>Task</code> object.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " | " + description;
    }
}
