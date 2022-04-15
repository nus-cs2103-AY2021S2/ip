package duke.task;


/**
 * Represents a Todo task as a special case of task. A <code>ToDo</code> object has two
 * fields, which are the task name and done-status. e.g., <code>go to school, false</code>
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo object
     * @param description The name of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDo object
     *
     * @param description The name of the todo task.
     * @param status The done-status of the todo task.
     * @param priority The priority of the todo task.
     */
    public ToDo(String description, boolean status, int priority) {
        super(description, status, priority);
    }

    /**
     * Gets the task name for a todo object.
     * @return A String object that represent the task name, including information
     * about the task type and task name.
     */
    @Override
    public String toString() {
        return "[T]" + super.getTaskName();
    }

}
