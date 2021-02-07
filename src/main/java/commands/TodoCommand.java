package commands;

import tasklist.TaskList;
import tasks.Todo;

public class TodoCommand extends Command {
    protected TodoCommand(String commandBody) {
        super("todo", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        String s = "";
        taskList.addTask(new Todo(commandBody));
    }
}
