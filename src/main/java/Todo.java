public class Todo extends Task {

    public Todo(boolean isDone, String description) {
        super("T", isDone, description);
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }
}
