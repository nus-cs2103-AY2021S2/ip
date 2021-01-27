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

    /**
     * Changes the description of the ToDoTask
     * @param newDescription New description of the task.
     * @return New ToDoTask with edited description
     */
    public ToDoTask editDescription(String newDescription) {
        return new ToDoTask(newDescription, this.isDone);
    }

    /**
     * Marks a ToDoTask as done.
     * @return ToDoTask that is marked as done.
     */
    public ToDoTask markAsDone() {
        if (!this.isDone) {
            return new ToDoTask(this.getDescription(), true);
        } else {
            throw new UnsupportedOperationException("This task is already done.\n" +
                    "I would have wanted to say Stonks...\n" +
                    "but your usage of an illegal operation is Not Stonks!");
        }
    }

    /**
     * Marks a ToDoTask as undone.
     * @return ToDoTask that is marked as undone.
     */
    public ToDoTask markAsUndone() {
        if (this.isDone) {
            return new ToDoTask(this.getDescription(), false);
        } else {
            throw new UnsupportedOperationException("This task is already not done. Not stonks anyway!");
        }
    }
}
