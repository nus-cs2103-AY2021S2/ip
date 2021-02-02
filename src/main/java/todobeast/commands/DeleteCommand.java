package todobeast.commands;

import todobeast.Storage;
import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.TaskNotFoundException;

public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        if (!taskList.isTaskIndexInRange(taskNumber)) {
            throw new TaskNotFoundException("Task with index " + taskNumber + " does not exist in the list!");
        }
        taskList.deleteTask(taskNumber);
        ui.showDeleted(taskList.getTask(taskNumber));
        ui.showNumOfTasks(taskList.getNumOfTasks());
    }
}
