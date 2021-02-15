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
     * Constructs a task.
     *
     * @param description String to describe task.
     * @param isDone Boolean for the done status for task.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon for a task based on whether it is done or not.
     *
     * @return String icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the done status of the task.
     *
     * @return boolean for whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Getter for the task description.
     * @return string for task description.
     */
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
