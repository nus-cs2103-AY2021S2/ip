public class Todo extends Task {
    private Todo() {
        super();
    }

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", "T", done ? "X" : " ", taskName);
    }
}
