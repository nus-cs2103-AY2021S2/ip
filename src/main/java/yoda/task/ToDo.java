package yoda.task;

/**
 * ToDo class that is a specialized version of the Task class and extends the Task class.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo object.
     * @param description Description of ToDo object.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Formats the ToDo information to be user-readable.
     * @return User-readable format of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
