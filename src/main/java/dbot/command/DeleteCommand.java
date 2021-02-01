package dbot.command;

import dbot.ui.Ui;
import dbot.exception.DukeException;
import dbot.storage.Storage;
import dbot.task.Task;
import dbot.tasklist.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private Task deleteTask;

    public DeleteCommand(int targetIndex) {
        setTargetIndex(targetIndex);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        quietExecute(tasks, storage);
        ui.printDelete(deleteTask);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) throws DukeException {
        deleteTask = tasks.remove(getTargetIndex() - 1);
    }
}
