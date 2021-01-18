/**
 * Represents a todo task without any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Creates an todo instance.
     *
     * @param description String describing the todo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String which gives information about the todo.
     *
     * @return A String containing information about the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}