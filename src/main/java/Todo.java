public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
