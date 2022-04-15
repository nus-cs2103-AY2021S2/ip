package duke.tasks;

/**
 * Represents a Task as a Todo.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
        this.taskType = "Todo";
    }

    /**
     * Constructor for Todo.
     * @param description Description of the Todo.
     * @param isDone Marks whether the Todo is Done.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
        this.taskType = "Todo";
    }

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
