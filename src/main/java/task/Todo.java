package task;

public class Todo extends Task {
    /**
     * Instantiates a new todo task.
     *
     * @param description the todo description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "TODO \u00BB " + super.toString();
    }
}
