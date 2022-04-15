package duke;

/**
 * A to-do task is a task which is expected to be done sometime in the future but does not have a set deadline
 */
public class ToDoTask extends Task {

    /**
     * Constructor for a to-do task
     * @param description Details of the task
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Displays the details of the task
     * @return Formatted string which shows the type, description and status of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
