package duke.task;

/**
 * Tasks can be added to the task list that the bot manages.
 *
 * @author  Nicole Ang
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object given a description.
     *
     * @param description   Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a 'X' if the task is marked as done and no symbol otherwise.
     *
     * @return Status icon of task to indicate whether it is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task specifying its description.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
