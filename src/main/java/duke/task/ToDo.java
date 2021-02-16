package duke.task;

/**
 * The todo class initialize and manage todo tasks.
 */

public class ToDo extends Task {

    /**
     * Create a todo task with description and status
     * @param description task description
     * @param isCompleted task status
     */
    public ToDo(String description, Boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Create a todo task with task description
     * @param description task description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of todo task to be added to data file
     * @return string representation of todo task
     */
    @Override
    public String changeFormat() {
        return "T" + super.changeFormat();
    }

    /**
     * Returns a customized representation of task to user
     * @return string representation of todo task to be displayed to the user
     */
    @Override
    public String toString() {
        return " Todo:" + super.toString();
    }
}
