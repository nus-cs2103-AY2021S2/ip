package duke.task;

/**
 * Represents a task without any date/time attached.
 */
public class ToDo extends Task {
    /**
     * Creates a task to be done, without any date/time attached.
     *
     * @param description Describes the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
