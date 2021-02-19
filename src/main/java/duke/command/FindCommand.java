package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a FindCommand.
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Constructs a FindCommand.
     * @param keyword The keyword to be used to find matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Collects all the matching tasks and calls ui to print therm.
     * @param tasks Task list given.
     * @param ui User interface class object.
     * @param storage Storage path that is going to be updated.
     * @return All the matching tasks in form of String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findMatchingTask(keyword);
        ui.listMatchingTasks(matchingTasks);
        return ui.listMatchingTasksResponse(matchingTasks);
    }

    /**
     * Returns false because this is not the ExitCommand.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
