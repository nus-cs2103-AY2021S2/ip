package duke.tasks;

/**
 * Todo is a task that can be done anytime. As such, it does not have a due date.
 */
public class Todo extends Task {

    /**
     * Initialises a todo with its description. The todo is not completed yet.
     *
     * @param description A String description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialises a todo with its description, and its specified completion status.
     *
     * @param description A String description of the todo.
     * @param isDone A boolean variable specifying if the todo is already completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Marks a todo as completed.
     *
     * @return A completed todo with the same description.
     */
    public Todo markAsDone() {
        return new Todo(this.description, true);
    }

    /**
     * Returns a String representation of the todo.
     *
     * @return A String representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
