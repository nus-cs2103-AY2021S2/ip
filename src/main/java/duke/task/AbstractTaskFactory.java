package duke;

/**
 * Abstract class for the generation of Tasks.
 */
abstract class AbstractTaskFactory {
    /**
     *
     * @param parameters Information needed to create task.
     * @return New generated task.
     * @throws IllegalArgumentException
     */
    protected abstract Task createTask(String parameters) throws IllegalArgumentException;

    /**
     * Generate Tasks depending on the type needed.
     *
     * @param parameters string representation of the task information.
     * @return duke.Task duke.Task generated.
     */
    protected Task getTask(String parameters) {
        return this.createTask(parameters);
    }
}
