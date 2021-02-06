/**
 * Todos are Tasks that must be done but without a time constraint.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
        super.taskType = TODO;
    }

    /**
     * Creates a string representation of the Todo object.
     * @return a string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
