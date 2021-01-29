package duke.task;

/**
 * Class for ToDo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs an undone ToDo java.duke.controller.task with a name.
     *
     * @param taskName Name of the java.duke.controller.task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a ToDo java.duke.controller.task with a name with status.
     *
     * @param taskName Name of the java.duke.controller.task.
     * @param done Status of the java.duke.controller.task, done or not done.
     */
    public ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    /**
     * Returns a string representation of the ToDo java.duke.controller.task.
     *
     * @return String representation of the ToDo java.duke.controller.task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parse the java.duke.controller.task to comply with CSV format.
     *
     * @return A string that complies with CSV format.
     */
    public String parseToCSVRow() {
        return "T," + super.isDone() + "," + super.getTaskName();
    }
}
