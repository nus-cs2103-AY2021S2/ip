/**
 * Represents a Todo Task.
 * @author Arihant Jain
 */
public class Todo extends Task {

    /**
     * Instantiates a new Todo.
     *
     * @param description the task description
     */
    public Todo(String description) {
        super(description);
    }

    public String getDescription() {
        return this.description;
    }
    /***
     * Get list variation Todo Task String.
     * @return String Todo Task String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
