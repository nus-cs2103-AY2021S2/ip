public abstract class AbstractTaskFactory {

    protected abstract Task createTask(String parameters) throws IllegalArgumentException;

    public Task getTask(String parameters) {
        return this.createTask(parameters);
    }

}
