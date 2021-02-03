package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Responsible for listing the contents of TaskList.
 */
public class ListCommand extends Command {
    /**
     * Returns the list of tasks in TaskList.
     *
     * @param tasks TaskList to be listed.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @return String listing the tasks in TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return ui.getEmptyListString();
        } else {
            return ui.getListOfTasks(tasks);
        }
    }
}
