package project.task;

/**
 * Represents a todo task in the application.
 * This extends the {@code Task} class.
 */
public class Todo extends Task {

    /**
     * Creates an instance of {@code Todo}.
     *
     * @param description The todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns {@code String} representation of this {@code Todo}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
