package duke.tasks;

/**
 * Todo is a type of Task which has a description.
 * It also maintains a isDone state.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of a Todo object when saving locally.
     *
     * @return String of Todo for saving locally.
     */
    public String saveString() {
        return isDone ? "T --- 1 --- " + description : "T --- 0 --- " + description;
    }

    /**
     * Returns string representation of Todo object.
     *
     * @return String of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
