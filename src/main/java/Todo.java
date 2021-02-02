/**
 * Represents a todo-task object that inherits from a task object.
 * Contains a description and status on whether the task is done.
 */
public class Todo extends Task {
    /**
     * Initializes a newly created todo-task object with a description.
     *
     * @param description Description of the task.
     * @param isDone Whether or not the task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns string object that formats the todo-task to be saved.
     * Format:
     * <p> Todo-task that is done: T | 1 | &lt;task_description&lt; </p>
     * <p> Todo-task that is not done: T | 0 | &lt;task_description&lt; </p>
     *
     * @return String representing todo-task in format to be saved into txt.
     */
    protected String saveTask() {
        return "T | " + super.saveTask();
    }

    /**
     * Converts this object to a string that represents the todo-task.
     *
     * @return String representing whether the todo-task is done and the todo-task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
