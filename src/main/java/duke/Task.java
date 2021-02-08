package duke;

/**
 *  The Task class contains methods for tasks
 */
public class Task {


    protected String description;
    protected boolean isDone;

    /**
     * Initialize a Task.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Status Icon if done is represented by a tick
     * If not done, represented by a x.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Mark Task as done, change icon from x to tick
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * String representation of Task object
     */
    public String toString() {
        return "[" +this.getStatusIcon() + "] " + this.description;
    }
}
