package ronald.command;

import ronald.storage.Storage;
import ronald.ui.Ui;

/**
 * Class containing data and methods specific to a List command.
 */
public class ListCommand extends Command {
    /**
     * Displays all current tasks.
     */
    @Override
    public void process() {
        Ui.displayAllTasks(Storage.getTasks());
    }
}
