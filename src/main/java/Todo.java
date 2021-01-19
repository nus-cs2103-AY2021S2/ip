public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
