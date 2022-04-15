package zeke;

/**
 * Todo class for todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructor for todo class.
     * Initializes a todo with specified description.
     *
     * @param description description of the todo task.
     */
    public Todo(String description) {
        super(description);
        this.type = 'T';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
