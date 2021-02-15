package chandler.command;

import chandler.ChandlerException;
import chandler.Parser;
import chandler.Storage;
import chandler.TaskList;
import chandler.task.Task;
import chandler.ui.Ui;

/**
 * The DeleteCommand class encapsulates information and methods about a DeleteCommand.
 */
public class DeleteCommand implements Command {
    private String[] fullCmdStrArray;
    private Ui ui;

    /**
     * Create and initialize a Delete Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying delete messages to the CLI.
     */
    public DeleteCommand(String fullCmd, Ui ui) {
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    /**
     *  * Processes the delete command by removing the specified task from the list of tasks,
     *  * removing it from the saved data file of tasks and displaying a message on the CLI.
     *
     * @param storage The storage object that writes data to the saved data file of tasks.
     * @param taskList The list of tasks.
     * @throws ChandlerException if the format of the Delete command is invalid.
     */
    @Override
    public String run(Storage storage, TaskList taskList) throws ChandlerException {
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Tasklist cannot be null";

        if (fullCmdStrArray.length > 2) { // too many parameters (>1)
            throw new ChandlerException(ui.deleteCmdTooManyArgsError());
        }

        if (fullCmdStrArray.length < 2) { // no parameter
            throw new ChandlerException(ui.deleteCmdNoArgsError());
        }

        if (!Parser.isNumber(fullCmdStrArray[1])) { // handle commands such as 'delete a', 'delete hello'
            throw new ChandlerException(ui.deleteCmdInvalidArgsError());
        }

        int taskIndex = Integer.parseInt(fullCmdStrArray[1]) - 1;
        if (taskIndex > taskList.getSize() - 1 || taskIndex < 0) {
            throw new ChandlerException(ui.taskDoesNotExistError());
        }
        Task deletedTask = taskList.getIndex(taskIndex);
        taskList.removeIndex(taskIndex);

        storage.saveTaskList(taskList);
        return ui.returnDeletedMsg(deletedTask, taskList);
    }
}
