package duke.task;

/**
 * An abstract class that contains a pair of description and done status, and the related logic.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    protected Task(final String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Serialises this task into a format that can be saved.
     *
     * Implementation dependent.
     * @return a serialised version of the task, implementation dependent.
     */
    public abstract String serialise();

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the done status of the task.
     *
     * @return the done status of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Searches the description for the given term.
     *
     * @param keyword the term to search for.
     * @return a boolean containing whether the description contains the term.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? '✓' : 'X', description);
    }
}
