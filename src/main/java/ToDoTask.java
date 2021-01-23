package surrealchat.task;

/**
 * Represents a task with no condition.
 */
public class ToDoTask extends Task {
    /**
     * Creates instance of ToDoTask object.
     * @param taskDescription The description of task.
     * @param isDone Boolean flag of whether task is done or not.
     */
    public ToDoTask(String taskDescription, boolean isDone) {
        super(taskDescription, "T", isDone);
    }
}
