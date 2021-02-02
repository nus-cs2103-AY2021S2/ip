package duke;

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
    public String saveTaskString() {
        return super.saveTaskString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
