package duke.command;

import duke.DukeException;
import duke.DukeHelper;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Lists all the tasks in the list to the user.
 */
public class ListCommand extends Command{

    private String date;

    public ListCommand() {
        this.date = null;
    }

    public ListCommand(String date) {
        this.date = date;
    }

    /**
     * Performs listing and printing of tasks to the user.
     * @param taskList user's task list
     * @param ui text UI object
     * @param storage storage object
     * @throws DukeException if there were errors encountered parsing the user's input
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int listSize = taskList.size();
        if (listSize <= 0) {
            throw new DukeException("Your task list is empty.");
        }

        //Clone the duke.task list for filtering
        TaskList printTaskList = taskList.clone();
        //If there is date in the duke.command, only display the events or deadlines on the particular date.
        if (date != null) {
            if(!date.isEmpty() || !date.isBlank())
                printTaskList = printTaskList.filterByDate(date);
        }

        if (printTaskList.size() <= 0) {
            throw new DukeException(String.format("You have no task on %s.", DukeHelper.formatDate(date)));
        }

        ui.printList(printTaskList);
    }
}
