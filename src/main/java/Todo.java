/**
 * Defines a task of type todo.
 */
public class Todo extends Task {

    /**
     * Constructs todo object.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats string to the save format.
     *
     * @return Formatted String.
     */
    @Override
    public String getSaveString() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
