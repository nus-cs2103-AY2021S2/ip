package monica.command;

import monica.Storage;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents a command that displays all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command.
     * @param tasks The task list used for execution of the command.
     * @param ui Interactions with users.
     * @param storage Data stored in the local file path.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
