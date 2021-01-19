public class ToDoFactory extends AbstractTaskFactory{
    @Override
    protected Task createTask(String parameters) {

        return new ToDo(parameters);
    }
}
