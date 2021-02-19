package duke.logic.commands;

import static duke.commons.util.CommandUtil.checkListIsEmpty;

import duke.commons.exceptions.DukeException;
import duke.model.task.TaskList;
import duke.storage.Storage;
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
        TaskList printTaskList = taskList.find(query);
        checkListIsEmpty(printTaskList, true);
        return new CommandResponse(ui.showListMessage(printTaskList, true));
    }
}
