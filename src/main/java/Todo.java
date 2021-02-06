/**
 * A Todo task that inherits Task.
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * A toString uniquely for Todo Task.
     *
     * @return Label for Todo - "T" and the description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
