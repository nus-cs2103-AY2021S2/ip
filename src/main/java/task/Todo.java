package task;
public class Todo extends Task {
    private Todo() {
        super();
    }

    /**
     * Constructs a Todo object.
     * @param taskName Name of the todo.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", "T", done ? "X" : " ", taskName);
    }
}
