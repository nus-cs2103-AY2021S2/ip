package surrealchat.task;

/**
 * Represents a task with no condition.
 */
public class ToDoTask extends Task {
    private ToDoTask(String taskDescription, boolean isDone, TaskPriority taskPriority) {
        super(taskDescription, "T", isDone, taskPriority);
    }

    /**
     * Creates new instance of ToDoTask object.
     * @param taskDescription The description of new task.
     * @return New ToDoTask that is not done.
     */
    public static ToDoTask createNewToDoTask(String taskDescription, TaskPriority taskPriority) {
        return new ToDoTask(taskDescription, false, taskPriority);
    }

    /**
     * Creates instance of ToDoTask based on what was loaded from file.
     * @param taskDescription The description of new task.
     * @param isDone Whether task was previously marked as done.
     * @return ToDoTask as loaded from file.
     */
    public static ToDoTask loadToDoTaskFromFile(String taskDescription, boolean isDone, TaskPriority taskPriority) {
        return new ToDoTask(taskDescription, isDone, taskPriority);
    }

    /**
     * Changes the description of the ToDoTask
     * @param newDescription New description of the task.
     * @return New ToDoTask with edited description
     */
    public ToDoTask editDescription(String newDescription) {
        return new ToDoTask(newDescription, isDone, taskPriority);
    }

    /**
     * Toggles a ToDoTask between done and undone.
     * @return ToDoTask that is marked as done/undone.
     */
    public ToDoTask markAsDone() {
        return new ToDoTask(getDescription(), !isDone, taskPriority);
    }
}
