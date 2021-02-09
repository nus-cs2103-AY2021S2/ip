package duke.commands;

import static duke.common.CommandUtils.assertInputs;
import static duke.common.CommandUtils.checkListIsEmpty;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Finds and lists all tasks in task lists that contains any of the argument keywords.
 */
public class FindCommand extends Command {

    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Retrieves all tasks in the task lists that contains any of the argument keywords.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @param storage storage object
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assertInputs(query);
        TaskList printTaskList = taskList.find(query);
        checkListIsEmpty(printTaskList, true);
        return new CommandResponse(ui.showListMessage(printTaskList, true));
    }
}
