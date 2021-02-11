package duke;

/**
 * An extension of the Task class that represent a specific task type.
 */
public class ToDo extends Task {

    /**
     * Construct a ToDo from a specific description.
     * @param task the task description
     */
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
