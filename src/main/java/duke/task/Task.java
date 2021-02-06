package duke.task;

/**
 * Represents a task in a task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new instance of <code>Task</code>.
     * The task is marked as not done by default.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, based on whether it is done or not.
     *
     * @return X for done or SPACE for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: \n  " + this);
    }

    /**
     * Returns a String representation of a task.
     *
     * @return String representation of a task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
