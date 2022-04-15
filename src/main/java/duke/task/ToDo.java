package duke.task;

/**
 * Class representing a ToDo task, a sub-class of Task.
 */
public class ToDo extends Task {

    /**
     * Constructor of the ToDo class.
     *
     * @param description A brief description of the ToDo task.
     */
    public ToDo(String description) {
        super(description, "0");
    }

    /**
     * Constructor of the ToDo class.
     *
     * @param description A brief description of the ToDo task.
     * @param isDone "0" if the task is not done. "1" if the task is done.
     */
    public ToDo(String description, String isDone) {
        super(description, isDone);
    }

    /**
     * Returns a String representation of the ToDo task to be displayed to the user.
     * Shows the description of the task, as well as whether it is done.
     *
     * @return A String representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
