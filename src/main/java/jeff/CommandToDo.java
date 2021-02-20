package jeff;

public class CommandToDo extends Command {

    CommandToDo (String main) {
        super(main);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        Task todo = new ToDo(this.getMainInfo());
        tasks.addTask(todo);
        return "Got it. I've added this task:\n" + todo + tasks.formatNumTasks();
    }
}
