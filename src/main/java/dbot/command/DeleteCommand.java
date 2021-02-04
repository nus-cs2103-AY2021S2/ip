package dbot.command;

import dbot.ui.Ui;
import dbot.exception.DBotException;
import dbot.storage.Storage;
import dbot.task.Task;
import dbot.tasklist.TaskList;

/**
 * A concrete subclass of Command that implements a Delete Command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private Task deleteTask;

    /**
     * Initializes a Deadline Command with the specified target index for a 0-indexed TaskList.
     *
     * @param targetIndex An integer representing the target index of a 0-indexed TaskList.
     */
    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DBotException {
        quietExecute(tasks, storage);
        ui.printDelete(deleteTask);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) throws DBotException {
        try {
            deleteTask = tasks.remove(getTargetIndex() - 1);
        } catch (IndexOutOfBoundsException e) {
            String errorMessage;
            if (tasks.size() > 0) {
                errorMessage = "Valid indexes are from 1 to " + tasks.size() + ".";
            } else {
                errorMessage = "The task list is empty and there is nothing to be deleted.";
            }
            throw new DBotException(errorMessage, e);
        }
    }
}
