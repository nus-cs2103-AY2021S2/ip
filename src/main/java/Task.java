/**
 * A base abstract class whose instances represent a unique Task.
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
    public String getStatusIcon() {
        String icon = isDone ? "\u2713" : "\u2718";
        return ("[" + icon + "]"); //return tick or X symbols within square brackets
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task status icon and description.
     *
     * @return A string containing the task status icon and description
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
