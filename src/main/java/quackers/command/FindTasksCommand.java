package quackers.command;

import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

/**
 * Represents the command to find tasks.
 */
public class FindTasksCommand extends Command {

    private static final boolean toExit = false;

    private String keyword;

    /**
     * Constructs a FindTasksCommand object.
     *
     * @param keyword Keyword to search.
     */
    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command and returns a response.
     *
     * @param tasks Core TaskList object.
     * @param storage Core Storage object.
     * @return CommandResponse object.
     */
    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) {
        String message = Ui.getTaskList(tasks.findTasks(this.keyword));
        return new CommandResponse(FindTasksCommand.class, message, FindTasksCommand.toExit);
    }
}
