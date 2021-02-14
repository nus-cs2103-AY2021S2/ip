package com.tanboonji.duke.model;

import java.io.Serializable;

/**
 * The Task class stores information about a task.
 */
public class Task implements Serializable {

    protected String description;
    protected boolean isDone;

    /**
     * Class constructor specifying the description of task.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as done.
     *
     * @return Task itself after getting marked as done.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Returns boolean value indicating if keyword can be found in task description.
     *
     * @param keyword Keyword to be searched in task description.
     * @return True if keyword can be found in task description, else false.
     */
    public boolean containsText(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
