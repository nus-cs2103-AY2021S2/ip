package duke;

/**
 * Represents the simplest type of Task that
 * contains only a description of the task
 * without date or time.
 */
public class ToDo extends Task {

    /**
     * Class constructor.
     *
     * @param description the details of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of this ToDo.
     *
     * @return a String representation of this ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
