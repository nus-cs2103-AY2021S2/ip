package vergil.types;

public class Todo extends Task {
    /**
     * Constructs a to-do task.
     * @param description a description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a to-do task.
     * @param description a description of the to-do task.
     * @param isDone the completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSaveString() {
        return String.format("t::%s", super.getSaveString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
