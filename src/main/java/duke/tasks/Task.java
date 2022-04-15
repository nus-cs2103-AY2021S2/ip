package duke.tasks;

/**
 * Task class.
 *
 * Bulk of the definition comes from the original webpage by CS2103 at
 * { @link https://nus-cs2103-ay2021s2.github.io/website/schedule/week2/project.html }.
 * General class representing all tasks. Should not be instantiated directly.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of Task.
     * @param isDone Whether task is completed.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns description of Task.
     *
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces description of Task.
     */
    public void setDescription(String d) {
        description = d;
    }

    /**
     * Returns whether task is done.
     *
     * @return True if task is done.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Marks task as done.
     *
     * No exception thrown if task is already marked as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * For pretty printing on stdout.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + getDescription();
    }
}
