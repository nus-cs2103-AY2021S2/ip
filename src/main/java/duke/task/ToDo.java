package duke.task;

/**
 * <code>ToDo</code> class represents any todo task with a description.
 */
public class ToDo extends Task {

    /**
     * Constructor for <code>ToDo</code> class
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super (description);
        this.taskType = "ToDo";
    }

    /**
     * Generates details of a todo task - description.
     * @return String output for a todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
