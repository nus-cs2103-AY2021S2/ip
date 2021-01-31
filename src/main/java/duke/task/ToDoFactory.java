package duke;

class ToDoFactory extends AbstractTaskFactory{
    @Override
    protected Task createTask(String parameters) throws IllegalArgumentException {
        if (parameters.isBlank()) {
            throw new IllegalArgumentException("Error: Description of todo cannot be empty");
        }
        return new ToDo(parameters);
    }
}
