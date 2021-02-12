package surrealchat.task;

/**
 * Represents a task with no condition.
 */
public class ToDoTask extends Task {
    private ToDoTask(boolean isDone, String taskDescription, TaskPriority taskPriority) {
        super("T", isDone, taskDescription, taskPriority);
    }

    /**
     * Creates new instance of ToDoTask object.
     * @param taskDescription The description of new task.
     * @param taskPriority Priority of task.
     * @return New ToDoTask that is not done.
     */
    public static ToDoTask createNewToDoTask(String taskDescription, TaskPriority taskPriority) {
        return new ToDoTask(false, taskDescription, taskPriority);
    }

    /**
     * Creates instance of ToDoTask based on what was loaded from file.
     * @param isDone Whether task was previously marked as done.
     * @param taskDescription The description of new task.
     * @param taskPriority Priority of task.
     * @return ToDoTask as loaded from file.
     */
    public static ToDoTask loadToDoTaskFromFile(boolean isDone, String taskDescription, TaskPriority taskPriority) {
        return new ToDoTask(isDone, taskDescription, taskPriority);
    }

    /**
     * Changes the description and priority of the ToDoTask.
     * @param newDescription New description of the task.
     * @param newPriority New task priority.
     * @return New ToDoTask with edited description and priority.
     */
    public ToDoTask editTask(String newDescription, TaskPriority newPriority) {
        return new ToDoTask(isDone, newDescription, newPriority);
    }

    /**
     * Changes the description of the ToDoTask.
     * @param newDescription The new description for the Task.
     * @return New ToDoTask with edited description.
     */
    public ToDoTask editDescription(String newDescription) {
        return editTask(newDescription, taskPriority);
    }

    /**
     * Changes the priority of the ToDoTask.
     * @param newPriority The new priority for the Task.
     * @return New ToDoTask with edited priority.
     */
    public ToDoTask editPriority(TaskPriority newPriority) {
        return editTask(description, newPriority);
    }

    /**
     * Toggles a ToDoTask between done and undone.
     * @return ToDoTask that is marked as done/undone.
     */
    public ToDoTask markAsDone() {
        return new ToDoTask(!isDone, getDescription(), taskPriority);
    }
}
