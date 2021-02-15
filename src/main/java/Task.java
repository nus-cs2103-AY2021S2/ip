/**
 * Represent a task added by a user.
 */
public class Task {

    /**
     * The content of the task.
     */
    protected String description;

    /**
     * A boolean deciding whether a task has been completed.
     * Task completion is triggered by the user.
     */
    protected boolean isDone;

    /**
     * Creates a new task with a given input by the user.
     * Task is initially not done at creation.
     *
     * @param description The input description for the task.
     * @param isDone      The input to see if the task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Get the status icon of the task.
     *
     * @return Return an icon, cross if done, empty space if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "-" : " ");
    }

    /**
     * Change the status of the the task completion to true when the task has been completed.
     */
    public void makeDone() {
        this.isDone = true;
    }

    /**
     * A toString to show the task information.
     *
     * @return Show the status of the task, together with its description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
