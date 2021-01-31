package duke.task;

public class ToDoFactory extends AbstractTaskFactory {
    @Override
    public Task createTask(String parameters) throws IllegalArgumentException {
        if (parameters.isBlank()) {
            throw new IllegalArgumentException("Error: Description of todo cannot be empty");
        }
        return new ToDo(parameters);
    }
}
