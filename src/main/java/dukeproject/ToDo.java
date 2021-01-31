package dukeproject;

/**
 * Represents a todo task where we can specific a task with description.
 * A todo object corresponds to a task with a description.
 *
 * X means that the task has been completed.
 */
public class ToDo extends Task {

    /**
     * Constructor for the Deadline task, specifying the description of the task.
     *
     * @param description Description of the to do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for the Deadline task, specifying the description of the task and
     * whether the task has been completed.
     *
     * @param description Description of the to do task.
     * @param isDone Determine whether the task is done or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a readable description of the task.
     * E.g. [T][X] Buy Lunch
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcon(), super.toString());
    }
}
