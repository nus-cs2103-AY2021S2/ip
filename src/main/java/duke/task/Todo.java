package duke.task;

/**
 * Todo class which creates a todo task.
 */

public class Todo extends Task {
    private String task;

    /**
     * Creates Todo task which keeps track of task details.
     *
     * @param task details of the todo task
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns details of the todo task.
     *
     * @return details of the task
     */
    @Override
    public String getTaskDetails() {
        return super.toString();
    }
}
