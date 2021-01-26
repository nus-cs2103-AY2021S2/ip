public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s\n", Command.TODO, super.toFileString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
