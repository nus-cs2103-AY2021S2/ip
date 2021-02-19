package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * ListCommand represent a command to list out all the current tasks in the list.
 */
class ListCommand extends Command {

    /**
     * Returns a ListCommand to list out tasks in the list.
     */
    ListCommand() {
        super(null, null, null, null, false);
    }

    /**
     * Outputs the current tasks to the user interface.
     *
     * @param tasks TaskList storing the current tasks.
     * @param ui The user interface of the program.
     * @param storage The storage of the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCurrentList(tasks.getList());
    }
}
