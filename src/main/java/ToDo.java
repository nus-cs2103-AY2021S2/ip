/**
 * A class that extends the Task class. An ToDo object corresponds to a Task object which has a description.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
