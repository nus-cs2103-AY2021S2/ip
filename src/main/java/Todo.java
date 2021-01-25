/**
 * This class extends the Task class and is used to
 * represent a single item that users can add to their list.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getDescription() {
        return "Todo`" + this.isDone + "`" + this.description;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}