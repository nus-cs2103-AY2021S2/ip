package dbot.task;

/**
 * A base abstract class whose instances represent a unique duke.task.Task.
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the relevant status icon for this task.
     *
     * @return A string containing this task's status icon
     */
    protected String getStatusIcon() {
        String icon = isDone ? "\u2713" : "\u2718";
        return ("[" + icon + "]"); //return tick or X symbols within square brackets
    }

    /**
     * Returns the Task description String.
     * @return The Task description String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the exact String required to Construct this Task.
     *
     * @return A String that can be used to construct an equivalent Task.
     */
    public abstract String getFullDescription();

    /**
     * Returns the isDone instance variable.
     *
     * @return The boolean isDone instance variable.
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
     * @return A string containing the task status icon and description
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
