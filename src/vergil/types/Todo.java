package vergil.types;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {
    /**
     * Creates a to-do task that is unfinished.
     *
     * @param   description a description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a to-do task with its completion status specified.
     *
     * @param   description a description of the task.
     * @param   isDone      the completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the task in the form of a save-file entry.
     * <p>
     *     The save-file entry's format is as follows:
     *     t::TASK_DESCRIPTION::TASK_IS_DONE
     * </p>
     *
     * @return  a string representing the task as a save-file entry.
     */
    @Override
    public String getSaveString() {
        return String.format("t::%s", super.getSaveString());
    }

    /**
     * Returns a string representation of the task.
     * <p>
     *     The string representation's format is as follows:
     *     [T][ ] TASK_DESCRIPTION, or
     *     [T][X] TASK_DESCRIPTION
     *     The 'X' represents the task's completion status: 'X' if completed; ' ' otherwise.
     * </p>
     *
     * @return  a string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
