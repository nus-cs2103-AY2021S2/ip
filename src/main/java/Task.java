/**
 * Represents the tasks that the user has input into the program. The task has a description and a boolean to
 * indicate that it is completed
 */
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the <code>Task</code>> to determine if it is done.
     *
     * @return a String of a Tick symbol if it is done and a Cross symbol if it is not
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the <code>Task</code> as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
