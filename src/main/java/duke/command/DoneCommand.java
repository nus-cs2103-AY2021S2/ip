package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * The DoneCommand class encapsulates information and methods about a DoneCommand.
 */
public class DoneCommand implements Command {
    private String[] fullCmdStrArray;
    private Ui ui;

    /**
     * Create and initialize a Done Command.
     *
     * @param fullCmd The full user input in String form.
     * @param ui The ui object responsible for displaying done to the CLI.
     */
    public DoneCommand(String fullCmd, Ui ui) {
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    /**
     *  * Processes the done command by marking the specified task as done,
     *  * updating it in the saved data file of tasks and displaying a message on the CLI.
     *
     * @param storage The storage object that writes data to the saved data file of tasks.
     * @param taskList The list of tasks.
     * @throws DukeException if the format of the Done command is invalid.
     */
    @Override
    public String run(Storage storage, TaskList taskList) throws DukeException {
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Tasklist cannot be null";

        if (fullCmdStrArray.length > 2) { // too many parameters (>1)
            throw new DukeException(ui.doneCmdTooManyArgsError());
        }

        if (fullCmdStrArray.length < 2) { // no parameter
            throw new DukeException(ui.doneCmdNoArgsError());
        }

        if (!Parser.isNumber(fullCmdStrArray[1])) { // handle commands such as 'done a', 'done hello'
            throw new DukeException(ui.doneCmdInvalidArgsError());
        }

        int taskIndex = Integer.parseInt(fullCmdStrArray[1]) - 1;
        if (taskIndex > taskList.getSize() - 1 || taskIndex < 0) {
            throw new DukeException(ui.taskDoesNotExistError());
        }
        Task doneTask = taskList.getIndex(taskIndex);
        doneTask.markDone();
        storage.saveTaskList(taskList);
        return ui.returnDoneMsg(doneTask);
    }

}
