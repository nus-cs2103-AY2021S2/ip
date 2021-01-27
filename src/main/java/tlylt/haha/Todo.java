package tlylt.haha;

/**
 * Representation of a Todo task.
 */
public class Todo extends Task {
    /**
     * Construct a Todo task.
     *
     * @param isDone      Whether task is set as completed.
     * @param description Details of task.
     */
    public Todo(boolean isDone, String description) {
        super("T", isDone, description);
    }

    /**
     * Returns string representation of task.
     *
     * @return String task representation.
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }
}
