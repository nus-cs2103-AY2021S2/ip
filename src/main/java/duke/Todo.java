package duke;

/**
 * A child of <code>Task</code> object, corresponds to a todo task with
 * description supplied by the user. eg., <code>buy cake</code>
 * @see Task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
