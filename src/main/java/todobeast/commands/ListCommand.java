package todobeast.commands;

import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.ToDoBeastException;

/**
 * A Command that lists all present tasks within the TaskList. Enclosing business logic is wrapped in the execute()
 * method.
 */
public class ListCommand extends Command {

    /**
     * Prints all tasks present within the TaskList.
     * @param taskList the TaskList that belongs to this application
     * @param ui the Ui that belongs to this application
     * @throws ToDoBeastException if the taskList is not found
     */
    public void execute(TaskList taskList, Ui ui) throws ToDoBeastException {
        if (taskList == null) {
            throw new ToDoBeastException("Task list cannot be found.");
        }
        ui.addToResponseOutput(ui.listTasks());
        ui.addToResponseOutput(ui.printTaskList(taskList.getTaskList()));
    }
}
