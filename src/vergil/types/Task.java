package vergil.types;

/**
 * Represents a general task with a description and a completion status.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new task that is unfinished.
     *
     * @param   description a description of the task.
     */
    public Task(String description) {
        assert description != null : "Description cannot be null.";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new task object with its completion status specified.
     *
     * @param   description a description of the task.
     * @param   isDone      the completion status of the task.
     */
    public Task(String description, boolean isDone) {
        assert description != null : "Description cannot be null.";
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Changes the task's state to done.
     */
    public void doTask() {
        isDone = true;
    }

    /**
     * Returns the task as part of a save-entry string.
     * <p>
     *     The save-file entry's format is as follows:
     *     TASK_DESCRIPTION::TASK_IS_DONE
     * </p>
     *
     * @return  a string representing part of a save-entry string.
     */
    public String getSaveString() {
        return String.format("%s::%b", description, isDone);
    }

    /**
     * Returns the task as part of a full string representation.
     * <p>
     *     The string representation's format is as follows:
     *     [ ] TASK_DESCRIPTION, or
     *     [X] TASK_DESCRIPTION
     *     The 'X' represents the task's completion status: 'X' if completed; ' ' otherwise.
     * </p>
     *
     * @return  a string representing part of a full string representation for the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
