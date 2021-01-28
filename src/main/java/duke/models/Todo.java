package duke.models;

/**
 * A subclass of Task, representing a todo task.
 */
public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of todo.
     * @return string representation of todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
