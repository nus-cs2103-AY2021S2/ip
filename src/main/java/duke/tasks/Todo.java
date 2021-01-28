package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of a Todo object when saving locally.
     * @return string of Todo for saving locally.
     */
    public String saveString() {
        return isDone ? "T --- 1 --- " + description : "T --- 0 --- " + description;
    }

    /**
     * Returns string representation of Todo object.
     * @return string of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
