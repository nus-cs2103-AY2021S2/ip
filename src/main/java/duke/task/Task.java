package duke.task;

/**
 * Task class created when user inputs todo, event, deadline
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * Constructor for Task class
     *
     * @param description Details of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Set state of task as done once the task is completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Retrieve the status of task
     *
     * @return Cross if task is marked as completed, empty string if incomplete
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2718" : " ");
    }

    /**
     * Retrieve the description of task
     *
     * @return String format of task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Format the task into custom format before saving into data file
     */
    public String formatTask() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Format task status icon and task details as string
     *
     * @return Task status icon and task details
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
