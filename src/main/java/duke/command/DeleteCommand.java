package duke.command;

import duke.*;
import duke.task.Task;
import duke.TaskList;

public class DeleteCommand implements Command {
    private String[] fullCmdStrArray;
    private Ui ui;

    public DeleteCommand(String fullCmd, Ui ui) {
        this.fullCmdStrArray = fullCmd.split(" ");;
        this.ui = ui;
    }

    @Override
    public void run(Storage storage, TaskList taskList) throws DukeException {
        if (fullCmdStrArray.length > 2) { // too many parameters (>1)
            throw new DukeException(ui.deleteCmdTooManyArgsError());
        }

        if (fullCmdStrArray.length < 2) { // no parameter
            String errorMsg = "Sorry human, please enter a task number.";
            throw new DukeException(errorMsg);
        }

        if (!Parser.isNumber(fullCmdStrArray[1])) { // handle commands such as 'delete a', 'delete hello'
            String errorMsg = "Sorry human, please enter the number of the task you want me to" +
                    "\n" +
                    "  " +
                    "delete.";
            throw new DukeException(errorMsg);
        }

        int taskIndex = Integer.parseInt(fullCmdStrArray[1]) - 1;
        if (taskIndex > taskList.getSize() - 1 || taskIndex < 0) {
            throw new DukeException("Sorry human, that task does not seem to exist.");
        }
        Task deletedTask = taskList.getIndex(taskIndex);
        taskList.removeIndex(taskIndex);
        storage.saveTaskList(taskList);
        ui.printDeletedMessage(deletedTask, taskList);
    }
}
