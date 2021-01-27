public class Todo extends Task {

    /**
     * Constructor method.
     * @param description String that describes the task.
     */
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides Task's toString method.
     * @return String output for the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}