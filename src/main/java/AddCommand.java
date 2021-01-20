import java.util.StringJoiner;

public class AddCommand implements ICommand {
    private TaskManager tasks;
    AbstractTaskFactory taskFactory;

    AddCommand(TaskManager tasks, AbstractTaskFactory taskFactory) {
        this.tasks = tasks;
        this.taskFactory = taskFactory;

    }

    @Override
    public void execute(String parameters) {
        try {
            Task newTask = taskFactory.getTask(parameters);
            this.tasks.addTask(newTask);
            System.out.println(newTask.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
