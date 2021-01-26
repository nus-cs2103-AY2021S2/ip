package duke;

/**
 * Encapsulates information and state of a Task.
 * Supports modifying status of a task.
 */
public abstract class Task {
    /** Text description of the task. */
    protected String description;
    /** State of the task. */
    protected boolean isDone;

    /**
     * Initialise a new undone Task with text description.
     * @param description Text represent of what task is about.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Give a text representation of task's state.
     @return an empty space for undone tasks and tick for done tasks.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    @Override
    public String toString() {
        return this.description;
    }
}
