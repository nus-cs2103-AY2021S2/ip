package rick;

/**
 * A child of <code>Task</code> object, corresponds to a todo task with
 * description supplied by the user. eg., <code>buy cake</code>
 *
 * @see Task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
