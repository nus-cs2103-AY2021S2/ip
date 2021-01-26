/**
 * Abstract class for the generation of Tasks.
 */
public abstract class AbstractTaskFactory {

    protected abstract Task createTask(String parameters) throws IllegalArgumentException;
    /**
     * Generate Tasks depending on the type needed.
     *
     * @param parameters string representation of the task information.
     * @return Task Task generated.
     */
    public Task getTask(String parameters) {
        return this.createTask(parameters);
    }

}
