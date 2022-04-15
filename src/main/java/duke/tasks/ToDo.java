package duke.tasks;

/**
 * Models a todo task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns String description of this ToDo task, identified by "[T]".
     *
     * @return String description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
