package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;

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
