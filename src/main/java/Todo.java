/**
 * This class extends the Task class and is used to
 * represent a single item that users can add to their list.
 */
public class Todo extends Task {
    /**
     * Overloaded constructor for Todo class.
     *
     * @param description description of Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor for Todo class.
     *
     * @param description description of Todo task.
     * @param isDone whether Todo is already completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Generates description to be saved in file for later retrieval.
     *
     * @return record of event to be saved in file.
     */
    @Override
    public String getDescription() {
        return "Todo`" + this.isDone + "`" + this.description;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}