/**
 * Defines a task which consists of a description and an indicator for
 * whether the task is complete.
 */
public class Task {
    /** Description of the task. */
    protected String description;

    /** Indicates whether the task has been completed.*/
    protected boolean isDone;


    /**
     * Constructs a task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns completion status icon.
     *
     * @return Completion status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *Marks a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     *Marks a task as incomplete.
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Formats string to be stored.
     *
     * @return Formatted String.
     */
    public String getSaveString() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
