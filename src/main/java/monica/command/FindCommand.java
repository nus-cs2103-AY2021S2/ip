package monica.command;

import monica.Storage;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents a command that displays all the tasks that contain a specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand class.
     * @param keyword Keyword to be found.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command.
     * @param tasks The task list used for execution of the command.
     * @param ui Interactions with users.
     * @param storage Data stored in the local file path.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFound(keyword, tasks);
    }
}
