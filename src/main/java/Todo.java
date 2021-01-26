public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getSaveString() {
        if (this.isDone()) {
            return String.format("todo [isDone] %s\n", description);
        } else {
            return String.format("todo %s\n", description);
        }
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatus(), description);
    }
}