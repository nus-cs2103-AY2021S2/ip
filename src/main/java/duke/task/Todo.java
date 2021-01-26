package duke.task;

/**
 * A Todo class which represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructs Todo.
     *
     * @param isDone      Determines if task is completed.
     * @param description Task description.
     */
    public Todo(int isDone, String description) {
        super('T', isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
