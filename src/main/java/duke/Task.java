package duke;

import java.io.Serializable;

/**
 * Represents a reminder of a task to be done or attended to.
 * A Task object contains a String description and a boolean flag
 * to check if it has been attended to.
 */
public class Task implements Serializable {

    protected String description;
    protected boolean isDone;

    /**
     * Class constructor.
     *
     * @param description the details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String that represents whether this Task is done.
     *
     * @return String representation of whether this Task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X or blank
    }

    /**
     * Marks this Task as done by changing the boolean flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of this Task.
     *
     * @return a String representation of this Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
