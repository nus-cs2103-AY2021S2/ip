/**
 * Todo task class that has no deadline or specified time slot.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task.
     *
     * @param description String that describes the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task.
     *
     * @param description description of the task.
     * @param isDone status of task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides Task's toString method.
     * @return String output for the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
