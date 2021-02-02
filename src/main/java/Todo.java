public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean b) {
        super(description, b);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}


