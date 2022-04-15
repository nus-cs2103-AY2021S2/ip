package duke.task;

/**
 * Factory Class responsible for creating Factory objects.
 */
public class ToDoFactory extends AbstractTaskFactory {

    /**
     * Creates a ToDo object using the parameters given.
     * @param parameters Information needed to create ToDo.
     * @return ToDo object
     * @throws IllegalArgumentException thrown when invalid arguments are given.
     */
    @Override
    public Task createTask(String parameters) throws IllegalArgumentException {
        if (parameters.isBlank()) {
            throw new IllegalArgumentException("Error: Description of todo cannot be empty");
        }
        return new ToDo(parameters);
    }
}
