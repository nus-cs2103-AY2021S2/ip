package com.nus.duke.data;

import java.io.Serializable;

/**
 * An abstract class Task in added by the user.
 */
public abstract class Task implements Serializable, Searchable {

    /**
     * Description of the task.
     */
    protected final String description;

    /**
     * Indicates if the task is completed.
     */
    protected boolean isDone;

    /**
     * Creates a new task with the provided description.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the status icon for the task. If done, returns a tick (unicode character), else it
     * will return space
     *
     * @return Status icon for task
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : " "); // return tick or empty
    }

    @Override
    public boolean containsText(String text) {
        return this.description.contains(text);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
