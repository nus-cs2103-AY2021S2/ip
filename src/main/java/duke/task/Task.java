package duke.task;

import java.time.LocalDate;

/**
 * The parent class for all types of tasks.
 */
public class Task {
    protected final String name;
    protected final TaskType type;
    protected boolean isDone;

    /**
     * Constructs a task object.
     *
     * @param name the task name
     * @param type the task type
     */
    Task(String name, TaskType type) {
        this.name = name;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Constructs a task object.
     *
     * @param name the task name
     * @param type the task type
     * @param isDone the status of the event
     */
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
     * Returns the time of the deadline.
     *
     * @return the deadline time to be returned
     */
    public LocalDate getTime() {
        return null;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns string format of the task.
     *
     * @return the string format of the task
     */
    @Override
    public String toString() {
        String returnString = "[" + type.getType() + "][" + (isDone ? "X" : " ") + "] ";
        return returnString + name;
    }
}
