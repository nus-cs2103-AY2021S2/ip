package jeff.command;

import java.util.ArrayList;

import jeff.JeffException;
import jeff.Storage;
import jeff.TaskList;
import jeff.task.Task;

public class CommandFind extends Command {

    public CommandFind(String main) {
        super(main);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        ArrayList<Task> foundTasks = tasks.findTask(getMainInfo());
        String s = "Here are the tasks I found matching \"" + getMainInfo() + "\":\n";
        for (Task t : foundTasks) {
            s += t.toString() + "\n";
        }
        return s;
    }
}
