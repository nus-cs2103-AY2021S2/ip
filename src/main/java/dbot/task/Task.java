package dbot.task;

/**
 * A base abstract class whose instances represent a unique Task.
 */
public abstract class Task {
    /** The Task description. */
    protected String description;
    /** A flag indicating whether the Task is done. */
    protected boolean isDone;

    /**
     * Instantiates a Task with the provided description.
     *
     * @param description A String containing the description of the Task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the relevant status icon for this task.
     *
     * @return A string containing this task's status icon.
     */
    protected String getStatusIcon() {
        String icon = isDone ? "\u2713" : "\u2718";
        return ("[" + icon + "]"); //return tick or X symbols within square brackets
    }

    /**
     * Returns the Task description String.
     *
     * @return The Task description String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the String required to construct a replicate of this Task.
     *
     * The replicate must have all its properties match the original Task, e.g. whether it is marked as done.
     *
     * @return A String that can be used to construct a replicate Task.
     */
    public abstract String getFullDescription();

    /**
     * Returns a boolean indicating whether the task is done.
     *
     * @return A boolean indicating whether the task is done.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Marks a task as done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the task status icon and description.
     *
     * @return A string containing the task status icon and description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
