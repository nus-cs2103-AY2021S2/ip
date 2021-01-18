/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Factory method for creating Todo task.
     * @param description Description of the task
     * @return A todo task
     * @throws DukeException if description is empty
     */
    public static Todo createTodo(String description) throws DukeException {
        if (description.length() == 0) {
                throw new DukeException("The description of Todo cannot be empty.");
        }
        return new Todo(description);
    }

    private Todo(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
