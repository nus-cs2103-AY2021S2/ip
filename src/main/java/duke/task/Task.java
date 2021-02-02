package duke.task;

/**
 * Represents a task item that can be added and listed in Duke.
 *
 * @author Aaron Saw Min Sern
 */
public abstract class Task {
    protected boolean isDone;
    protected final String description;
    public abstract String encode();

	/**
	 * Default constructor for subclasses of Task.
	 *
	 * @param	isDone      the status of the task item 
	 * @param	description	the description for the task item
	 */
    protected Task(final boolean isDone, final String description) {
        this.isDone = isDone;
        this.description = description;
    }

	/**
	 * Returns a tick if this Task object is finished and a cross otherwise.
	 *
     * @return              the status icon based on the status of this Task object.
	 */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marked the status of this Task object as finished.
     */
    public void markAsDone() {
        this.isDone = true;
    }

	/**
	 * Returns the string represenatation of this Task instance
	 *
	 * @return		the string representation of this Task instance
	 */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
