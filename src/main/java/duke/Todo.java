package duke;

/**
 * Represents a task with a description.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo object with a description.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskCommand() {
        return "t " + getDescription();
    }
}
