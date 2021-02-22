package jeff.command;

import jeff.JeffException;
import jeff.Storage;
import jeff.TaskList;
import jeff.task.Task;
import jeff.task.ToDo;

public class CommandToDo extends Command {

    public CommandToDo (String main) {
        super(main);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        Task todo = new ToDo(this.getMainInfo());
        tasks.addTask(todo);
        return "Got it. I've added this task:\n" + todo + tasks.formatNumTasks();
    }
}
