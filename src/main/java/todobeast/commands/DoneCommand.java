package todobeast.commands;

import todobeast.Storage;
import todobeast.tasks.Task;
import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.TaskNotFoundException;

public class DoneCommand extends Command {
    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        if (!taskList.isTaskIndexInRange(taskNumber)) {
            throw new TaskNotFoundException("Task with index " + taskNumber + " does not exist in the list!");
        }

        Task currentTask = taskList.getTask(taskNumber);
        taskList.setTaskAsDone(currentTask);
        ui.showDone(currentTask);
    }
}

