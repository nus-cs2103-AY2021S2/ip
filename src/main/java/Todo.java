/**
 * Represents a Task Object that is to be done without any conditions.
 */
public class Todo extends Task {
    /**
     * Constructor for this Todo object.
     *
     * @param   description  Task Description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo Task.
     *
     * @return type of task, task completion status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
