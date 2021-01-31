package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents ExitCommand
 */
public class ExitCommand extends Command {
    /**
     * Calls ui to print a farewell message.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayGoodBye();
    }

    /**
     * Returns true.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
