package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Responsible for listing the contents of TaskList.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {}

    /**
     * Lists the tasks in TaskList, shows the overview of all tasks.
     *
     * @param tasks TaskList to be listed.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showEmptyList();
        } else {
            ui.showTasks(tasks);
        }
    }

    /**
     * Returns if program should exit after this command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
