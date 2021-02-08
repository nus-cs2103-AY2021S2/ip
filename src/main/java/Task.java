/**
 * Represents a task object.
 * Contains a description and status on whether the task is done.
 */
public class Task implements Comparable<Task> {

    /** Stores the description of this Task. */
    protected String description;

    /** Represents whether this Task is done. */
    protected boolean isDone;

    /**
     * Initializes a newly created Task object with a description.
     *
     * @param description Description of the task.
     * @param isDone Whether or not the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns description of task.
     *
     * @return String representing description of the task.
     */
    protected String getDescription() {
        return this.description;
    }

    /**
     * Checks whether the task is done or not.
     *
     * @return String representing a tick if the task is done, else a String of a space.
     */
    protected String getStatusIcon() {
        if (isDone) {
            return ("\u2713"); //return tick
        } else {
            return (" ");
        }
    }

    /**
     * Checks whether the task is done or not.
     *
     * @return Boolean representing whether the task is done.
     */
    protected boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns string object that formats the task to be saved.
     * Format:
     * <p> Task that is done: 1 | &lt;task_description&lt; </p>
     * <p> Task that is not done: 0 | &lt;task_description&lt; </p>
     *
     * @return String representing task in format to be saved into txt.
     */
    protected String saveTask() {
        int done = isDone ? 1 : 0;
        return done + " | " + this.description;
    }

    /**
     * Compares 2 tasks by task description.
     *
     * @param task Task that this task is being compared to.
     * @return Integer that corresponds to the order of task.
     */
    public int compareTo(Task task) {
        String myDescription = this.getDescription();
        String otherDescription = task.getDescription();
        return myDescription.compareTo(otherDescription);
    }

    /**
     * Converts this object to a string that represents the task.
     *
     * @return String representing whether the task is done and the task description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
