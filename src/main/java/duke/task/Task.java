package duke.task;

/**
 * The parent class for all types of tasks.
 */
public class Task {
    protected final String name;
    protected final TaskType type;
    protected boolean done;

    Task(String name, TaskType type) {
        this.name = name;
        this.done = false;
        this.type = type;
    }

    Task(String name, TaskType type, boolean done) {
        this.name = name;
        this.done = done;
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
        return done;
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
        done = true;
    }

    @Override
    public String toString() {
        String returnString = "[" + type.getType() + "][" + (done ? "X" : " ") + "] ";
        return returnString + name;
    }
}
