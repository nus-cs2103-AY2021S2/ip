package monica.command;

import monica.Storage;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents a command that displays all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes list command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
