public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), getContent());
    }
}
