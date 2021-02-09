package duke;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task {

    /**
     * Returns a Todo Task.
     *
     * @param description description of the task.
     * @param taskType the type of the task.
     */
    public Todo(String description, TaskType taskType) {
        super(description, taskType);
    }

    @Override
    public Task copy() {
        return new Todo(this.description, this.taskType);
    }

    @Override
    public String saveTaskString() {
        return super.saveTaskString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
