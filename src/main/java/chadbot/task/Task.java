package chadbot.task;

/**
 * The Task class represents a single task created by the user via user input to the Duke program.
 * It contains functions which enable the user to mark the task as done.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
 */
public abstract class Task {
    /** Description of the task. */
    protected String name;

    /** Indicates whether the task has been done by the user. */
    protected boolean isDone;

    /**
     * Default constructor for the Task class.
     *
     * @param name Description of the task.
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Checks whether the task has been marked as done.
     *
     * @return True if the task has been marked as done, and false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the isDone field to true, indicating that the task has been marked as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the name (description) of the task.
     *
     * @return The name (description) of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies the description of the Task.
     */
    public void setName(String name) {
        this.name = name;
    }

}
