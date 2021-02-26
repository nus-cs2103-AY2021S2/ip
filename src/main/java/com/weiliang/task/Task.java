package com.weiliang.task;

/**
 * The default to-do task.
 */
public class Task {

    protected String task;
    protected boolean isComplete;

    /**
     * Initializes an instance of the task.
     *
     * @param task The description.
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Marks the given task as complete.
     */
    public void markComplete() {
        this.isComplete = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description.
     */
    public String getDescription() {
        return task;
    }

    /**
     * Returns {@code true} if the task is completed, {@code false} otherwise.
     *
     * @return The completion status.
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Returns an alternative string formatting of the task, using vertical bars.
     *
     * @return String formatting.
     */
    public String toFormattedString() {
        return "T | " + (isComplete ? 1 : 0) + " | " + task;
    }

    /**
     * Returns the {@link String} representation of the task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T][" + (isComplete ? "X" : " ") + "] " + task;
    }

}
