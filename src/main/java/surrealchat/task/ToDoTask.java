package surrealchat.task;

/**
 * Represents a task with no condition.
 */
public class ToDoTask extends Task {
    private ToDoTask(String taskDescription, boolean isDone) {
        super(taskDescription, "T", isDone);
    }

    /**
     * Creates new instance of ToDoTask object.
     * @param taskDescription The description of new task.
     * @return New ToDoTask that is not done.
     */
    public static ToDoTask createNewToDoTask(String taskDescription) {
        return new ToDoTask(taskDescription, false);
    }

    /**
     * Creates instance of ToDoTask based on what was loaded from file.
     * @param taskDescription The description of new task.
     * @param isDone Whether task was previously marked as done.
     * @return ToDoTask as loaded from file.
     */
    public static ToDoTask loadToDoTaskFromFile(String taskDescription, boolean isDone) {
        return new ToDoTask(taskDescription, isDone);
    }

    /**
     * Changes the description of the ToDoTask
     * @param newDescription New description of the task.
     * @return New ToDoTask with edited description
     */
    public ToDoTask editDescription(String newDescription) {
        return new ToDoTask(newDescription, isDone);
    }

    /**
     * Toggles a ToDoTask between done and undone.
     * @return ToDoTask that is marked as done/undone.
     */
    public ToDoTask markAsDone() {
        return new ToDoTask(getDescription(), !isDone);
    }
}
