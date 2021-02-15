package antonio.task;

/**
 * Represents a to-do task.
 */
public class ToDoTask extends Task {

    /**
     * Constructor for to-do task.
     * @param description Name of the command.
     * @param id ID of task.
     */
    public ToDoTask(String description, int id) {
        super(description, id);
    }

    /**
     * Constructor for to-do task.
     * @param description Name of the command.
     * @param id ID of task.
     * @param status Completion status of the task.
     */
    public ToDoTask(String description, int id, int status) {
        super(description, id);
        super.isDone = status > 0;
    }

    @Override
    public String toString() {
        return "[T]" + super.checkBoxToString() + description;
    }
}
