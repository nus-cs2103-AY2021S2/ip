public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    static Todo getTodo(String description) {
        return new Todo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
