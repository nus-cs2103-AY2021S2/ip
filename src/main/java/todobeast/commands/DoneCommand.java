package todobeast.commands;

import todobeast.tasks.Task;
import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.TaskNotFoundException;

/**
 * A Command that represents marking a task as done in the TaskList. Enclosing business logic is wrapped in the
 * execute() method.
 */
public class DoneCommand extends Command {
    /**
     * The index of the task to be marked as done
     */
    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Handles the marking of a task with the specified index as done within the application.
     * @param taskList the TaskList that belongs to this application
     * @param ui the Ui that belongs to this application
     * @throws TaskNotFoundException if the task index provided does not exist within the task list.
     */
    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        if (!taskList.isTaskIndexInRange(taskNumber)) {
            throw new TaskNotFoundException("Task with index " + taskNumber + " does not exist in the list!");
        }

        Task currentTask = taskList.getTask(taskNumber);
        taskList.setTaskAsDone(currentTask);
        ui.showDone(currentTask);
    }
}

