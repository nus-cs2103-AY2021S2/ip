package todobeast.commands;

import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.TaskNotFoundException;
import todobeast.tasks.Task;

/**
 * A Command that represents deleting a task from the TaskList. Enclosing business logic is wrapped in the execute()
 * method.
 */
public class DeleteCommand extends Command {
    /**
     * The index of the task to be deleted
     */
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Handles the deletion of a task with the specified index within the application.
     * @param taskList the TaskList that belongs to this application
     * @param ui the Ui that belongs to this application
     * @throws TaskNotFoundException if the task index provided does not exist within the task list.
     */
    public void execute(TaskList taskList, Ui ui) throws TaskNotFoundException {
        if (!taskList.isTaskIndexInRange(taskNumber - 1)) {
            throw new TaskNotFoundException("Task with index " + taskNumber + " does not exist in the list!");
        }
        Task deletedTask = taskList.deleteTask(taskNumber);
        ui.addToResponseOutput(ui.showDeleted(deletedTask));
        ui.addToResponseOutput(ui.showNumOfTasks(taskList.getNumOfTasks()));
    }
}
