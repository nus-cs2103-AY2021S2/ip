package duke.tasks;

/**
 * An abstract class representing a general "task".
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Initializes a Task with a description.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Retrieves the status of the Task, i.e. whether it is done or not.
     *
     * @return True is Task is done, or false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Retrieves the Task's description.
     *
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.description;
    }
}
