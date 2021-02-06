package duke.task;

/**
 * Represents a todo task.
 */
public class ToDoTask extends Task {

    /**
     * Creates a new instance of <code>ToDoTask</code>.
     * @param description Description of todo task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns String representation of todo task.
     * @return String representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }
}
