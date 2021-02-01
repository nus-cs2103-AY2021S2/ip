package dbot.command;

import dbot.ui.Ui;
import dbot.exception.DukeException;
import dbot.storage.Storage;
import dbot.task.Task;
import dbot.tasklist.TaskList;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private Task doneTask;

    public DoneCommand(int targetIndex) {
        setTargetIndex(targetIndex);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        quietExecute(tasks, storage);
        ui.printDone(doneTask);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) throws DukeException {
        doneTask = tasks.get(getTargetIndex() - 1);
        doneTask.setDone(true);
    }
}
