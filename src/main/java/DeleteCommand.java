import java.io.IOException;

/**
 * Command to delete a task.
 * Inherits from te superclass Command.
 */
public class DeleteCommand extends Command {
    private final int taskNum;

    /**
     * Constructor to keep track of the task number to be deleted.
     * @param taskNum The task number that is to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task and update the user of what is being deleted.
     * @param taskList TaskList that contains all tasks at hand.
     * @param ui Ui that deals with interaction with the user.
     * @param storage Storage that deals with storing TaskList into hard drive.
     * @throws IOException If there is an error while updating the file in hard drive.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task toDelete = taskList.getTasks().get(this.taskNum - 1);
        taskList.deleteTask(this.taskNum - 1);
        storage.update(taskList);
        return ui.printDeleteTask(toDelete, taskList);
    }
}
