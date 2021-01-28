package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * A class represents a ListCommand.
 */
public class ListCommand extends Command {
    /**
     * Calls ui to print all the tasks in the task list right now.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }

    /**
     * Returns false.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
