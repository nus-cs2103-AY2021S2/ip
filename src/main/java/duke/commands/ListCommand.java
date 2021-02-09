package duke.commands;

import static duke.common.CommandUtils.checkListIsEmpty;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Lists all the tasks in the list to the user.
 */
public class ListCommand extends Command {

    private final String queryDate;

    /**
     * Constructor for List {@code Command}, queryDate is null by default otherwise specified.
     *
     * @param queryDate date to be queried
     */
    public ListCommand(String ... queryDate) {
        String newQueryDate = null;
        if (queryDate.length > 0) {
            newQueryDate = queryDate[0];
        }

        this.queryDate = newQueryDate;
    }

    /**
     * Performs listing and printing of tasks to the user.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @param storage storage object
     * @throws DukeException if there were errors encountered parsing the user's input
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        checkListIsEmpty(taskList, false);
        TaskList printTaskList = taskList;
        if (queryDate != null) {
            printTaskList = taskList.filterByDate(queryDate);
        }
        checkListIsEmpty(printTaskList, true);

        return new CommandResponse(ui.showListMessage(printTaskList, false));
    }
}
