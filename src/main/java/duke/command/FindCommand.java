package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * FindCommand represent a command to find task(s) that matches the keyword.
 */
public class FindCommand extends Command {

    /**
     * Returns a FindCommand object that will find all the tasks
     * matching the keyword.
     *
     * @param keyword The keyword to be matched.
     */
    public FindCommand(String keyword) {
        super(null, keyword, null, null, false);
    }

    /**
     * Outputs all the tasks that matches the keyword to the user interface.
     *
     * @param tasks TaskList storing the current tasks.
     * @param ui The user interface of the program.
     * @param storage The storage of the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindKeywordList(tasks.findTasksWithKeyword(description));
    }

}
