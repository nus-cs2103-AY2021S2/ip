package quackers.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Task object.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the task string representation.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
