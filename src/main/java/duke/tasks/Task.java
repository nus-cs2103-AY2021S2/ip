package duke.tasks;

/**
 * Task is a parent class for inheriting by specific tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises a task with its description. The task is not completed yet.
     *
     * @param description A String description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialises a task with its description, and its specified completion status.
     *
     * @param description A String description of the task.
     * @param isDone A boolean variable specifying if the task is already completed.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description, in String, of the task.
     *
     * @return A String description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a boolean variable indicating the completion status of the task.
     *
     * @return A boolean variable indicating the completion status of the task.
     */
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Marks a task as completed.
     *
     * @return A completed task with the same description.
     */
    public Task markAsDone() {
        return new Task(this.description, true);
    }

    /**
     * Returns a String representation of the task.
     *
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}
