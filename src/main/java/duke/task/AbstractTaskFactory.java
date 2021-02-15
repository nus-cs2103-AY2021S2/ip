package duke.task;

/**
 * Abstract class for the generation of Tasks.
 */
public abstract class AbstractTaskFactory {
    /**
     *
     * @param parameters Information needed to create task.
     * @return New generated task.
     * @throws IllegalArgumentException
     */
    public abstract Task createTask(String parameters) throws IllegalArgumentException;

    /**
     * Generate Tasks depending on the type needed.
     *
     * @param parameters string representation of the task information.
     * @return duke.task.Task duke.task.Task generated.
     */
    public Task getTask(String parameters) {
        return this.createTask(parameters);
    }
}
