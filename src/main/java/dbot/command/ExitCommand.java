package dbot.command;

import dbot.ui.Ui;
import dbot.storage.Storage;
import dbot.tasklist.TaskList;

/**
 * A concrete subclass of Command that implements an Exit Command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Instantiates an Exit Command.
     */
    public ExitCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Storage.StorageOperationException {
        storage.save(tasks);
        ui.showExitMessage();
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) {
        throw new IllegalArgumentException("The EXIT command cannot be used quietly.");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
