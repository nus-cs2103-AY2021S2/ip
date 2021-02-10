/**
 * Abstract parent class for all tasks including todos, events, deadlines.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description String to describe task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task.
     *
     * @param description String to describe task.
     * @param isDone Boolean for the done status for task.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Status icon for the task based on whether it is done or not.
     *
     * @return icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Overrides toString method.
     * @return Prints out task description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}
