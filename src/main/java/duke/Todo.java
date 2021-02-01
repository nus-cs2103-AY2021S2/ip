package duke;

/**
 * A subclass of Task, used for tasks with no timing.
 */
public class Todo extends Task {
    protected static final String TAG = "[T]";

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Todo.TAG + super.toString();
    }
}
