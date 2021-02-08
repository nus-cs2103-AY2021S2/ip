package duke.tasks;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Initializes a to-do task with a description.
     *
     * @param description Description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Retrieves the to-do task's description and status.
     *
     * @return A formatted string displaying the to-do task's description and status.
     */
    public String getStatusString() {
        return "[T]" + super.getStatusString();
    }
}
