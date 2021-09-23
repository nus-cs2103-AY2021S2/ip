package dbot.command;

import dbot.ui.Ui;
import dbot.storage.Storage;
import dbot.task.Task;
import dbot.tasklist.TaskList;
import dbot.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private Task task;

    public TodoCommand(String description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        quietExecute(tasks, storage);
        return ui.printAddTask(task);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) {
        task = new Todo(getDescription());
        task.setDone(getIsDone());
        tasks.add(task);
    }
}
