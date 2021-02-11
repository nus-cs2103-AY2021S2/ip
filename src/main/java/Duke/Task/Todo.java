package Duke.Task;

/**
 * A Todo is a task without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * The Todo class constructor has 1 parameter: the description about a specific task.
     * @param description The description about the task.
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Gets the string representation for this Todo object.
     * @return The string representation for this object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
