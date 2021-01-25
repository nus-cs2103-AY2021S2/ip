package duke.command;

import duke.exception.*;
import duke.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.Ui;

public class DeleteTask extends Command {

    public DeleteTask(String commandType, int index) {
        super.commandType = commandType;
        super.commandDetails = "";
        super.dateTime = "";
        super.outputMessage = "";
        super.index = index;
    }

    public void handleDeleteTask(TaskList taskList) {
        Task deleteTask = taskList.get(index - 1);
        taskList.remove(deleteTask);
        this.outputMessage = "Noted. I've removed this task: \n" + "\t  " + deleteTask.toString()
                + "\n\t Now you have " + taskList.size() + " tasks in the list.";
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > taskList.size()) {
            throw new DukeException(ExceptionType.INVALID_INTEGER, "");
        }
        handleDeleteTask(taskList);
        storage.saveData(taskList);
        ui.display(outputMessage);
    }

    @Override
    public boolean continueInput() {
        return true;
    }
}
