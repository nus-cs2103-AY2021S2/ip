package mike.task;

public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Creates a Task with a description
     *
     * @param description the description of the task
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon of the status of completion of the task
     *
     * @return a string indicating the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }
}
