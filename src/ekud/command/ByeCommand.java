package ekud.command;

import ekud.common.exception.DukeException;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.ui.Ui;

/**
 * Command that exits the application.
 */
public class ByeCommand extends Command {
    /**
     * Save the current list of tasks to disk.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The file writer.
     */
    @Override
    public void execute(final TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);  // probably can skip since tasks are written to disk on every modification
        ui.printLines("Bye bye. Anything call me ah!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
