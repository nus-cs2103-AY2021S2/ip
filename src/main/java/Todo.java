public class Todo extends Task {
    public Todo(String input) {
        super(input);
        if (input.length() == 0) {
            throw new IllegalArgumentException();
        }
    }

    public Todo(String input, boolean isDone) {
        super(input, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
