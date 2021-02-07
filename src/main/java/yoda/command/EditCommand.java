package yoda.command;

import yoda.storage.Storage;
import yoda.task.Task;
import yoda.task.TaskList;
import yoda.ui.Ui;

/**
 * EditCommand class that handles task states and a child of the Command class.
 */
public class EditCommand extends Command {
    /**
     * Creates an EditCommand object.
     * @param details Details of EditCommand object.
     */
    public EditCommand(String[] details) {
        super(details);
        commandType = CommandType.valueOf(details[0]);
    }

    /**
     * Deletes a task or marks a task as done based on the command issued by user.
     * @param taskList TaskList associated with the EditCommand being executed.
     * @param ui Ui associated with the EditCommand being executed.
     * @param storage Storage associated with the EditCommand being executed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        int taskToBeEdited = Integer.parseInt(details[1]) - 1;
        Task task = taskList.accessTask(taskToBeEdited);
        if (commandType == CommandType.DELETE) {
            taskList.deleteTask(taskToBeEdited);
            return ui.printDeletedTask(task, taskList.length());
        } else if (commandType == CommandType.DONE) {
            taskList.markTaskAsDone(taskToBeEdited);
            return ui.printFinishedTask(task, taskList.length());
        } else {
            return "The greatest teacher, failure is.";
        }
    }
}
