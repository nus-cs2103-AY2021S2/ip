package duke.command;

import duke.exception.*;
import duke.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String commandType, int index) {
        super.commandType = commandType;
        super.commandDetails = String.valueOf(index);
        super.dateTime = "";
        super.outputMessage = "";
        super.index = index;
    }

    private void markDoneTask(TaskList taskList) {
        Task doneTask = taskList.get(this.index - 1);
        doneTask.markAsDone();
        this.outputMessage = " Nice! I've marked this task as done:\n" + "\t  " + doneTask.toString();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > taskList.size()) {
            throw new DukeException(ExceptionType.INVALID_INTEGER, "");
        }
        markDoneTask(taskList);
        storage.saveData(taskList);
        ui.display(outputMessage);
    }

    @Override
    public boolean continueInput() {
        return true;
    }
}
