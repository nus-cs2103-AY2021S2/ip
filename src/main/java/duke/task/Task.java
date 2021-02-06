package duke.task;

/**
 * The parent class for all types of tasks.
 */
public class Task {
    protected final String name;
    protected final TaskType type;
    protected boolean isDone;

    Task(String name, TaskType type) {
        this.name = name;
        this.isDone = false;
        this.type = type;
    }

    Task(String name, TaskType type, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Returns the name of the task.
     *
     * @return the task name to be returned
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether or not the task is done.
     *
     * @return whether or not the task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the type of the task.
     *
     * @return the task type to be returned
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String returnString = "[" + type.getType() + "][" + (isDone ? "X" : " ") + "] ";
        return returnString + name;
    }
}
