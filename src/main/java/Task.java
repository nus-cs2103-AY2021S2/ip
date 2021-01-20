/**
 * Task class created when user inputs todo, event, deadline
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param description details of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieve the status of task
     * @return Cross if task is marked as completed, empty string if incomplete
     */
    public String getStatusIcon() {
        return (this.isDone ?"\u2718" : " ");
    }

    /**
     * Retrieve the details of task
     * @return details of the task in String format
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set state of task as done once the task is completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Format task status icon and task details as string
     * @return task status icon and task details
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}