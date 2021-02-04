package task;

/**
 * Class <code>Task</code> represents a <code>Task</code>object in a task list. It contains descriptions, type
 * and isDone variables which describes the task
 */


public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task() {
    }

    /**
     * Returns the type of the task
     *
     * @return type of the task.
     */
    public String getType() {
        return this.type;
    }


    /**
     * Returns the status icon depending on the progress of the task.
     *
     * @return status icon
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Mark the task as complete
     */
    public void setComplete() {
        this.isDone = true;
    }

    /**
     * Returns the status of the task
     *
     * @return task status
     */
    public String getStatus() {
        return (isDone ? "complete" : "incomplete");
    }

    /**
     * Returns the task's description
     *
     * @return task's description
     */
    public String getDetails() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
