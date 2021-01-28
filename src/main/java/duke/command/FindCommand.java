package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFound(keyword, tasks);
    }

    /**
     * Returns a boolean value to signal the chat bot to exit.
     * @return False as the command does not signal the bot to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
