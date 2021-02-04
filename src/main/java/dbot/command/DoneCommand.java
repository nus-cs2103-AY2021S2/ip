package dbot.command;

import dbot.ui.Ui;
import dbot.exception.DBotException;
import dbot.storage.Storage;
import dbot.task.Task;
import dbot.tasklist.TaskList;

/**
 * A concrete subclass of Command that implements a Done Command.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private Task doneTask;

    /**
     * Initializes a Done Command with the specified target index for a 0-indexed TaskList.
     *
     * @param targetIndex An integer representing the target index of a 0-indexed TaskList.
     */
    public DoneCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DBotException {
        quietExecute(tasks, storage);
        ui.printDone(doneTask);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) throws DBotException {
        try {
            doneTask = tasks.get(getTargetIndex() - 1);
            doneTask.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            String errorMessage;
            if (tasks.size() > 0) {
                errorMessage = "Valid indexes are from 1 to " + tasks.size() + ".";
            } else {
                errorMessage = "The task list is empty and there is nothing to be marked as done.";
            }
            throw new DBotException(errorMessage, e);
        }
    }
}
