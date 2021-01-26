abstract class AbstractTaskFactory {

    protected abstract Task createTask(String parameters) throws IllegalArgumentException;

    protected Task getTask(String parameters) {
        return this.createTask(parameters);
    }
}
