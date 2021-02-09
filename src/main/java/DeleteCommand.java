/**
 * Specifies the command for delete command type.
 */
public class DeleteCommand extends Command {

    /**
     * Initialises DeleteCommand object.
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Executes the command by removing the task to the existing taskList,
     * writing the updated taskList into storage and responding with relevant message.
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.deleteTask(index);
        storage.writeToFile(taskList.getList());
        ui.showTaskDeleted(task);
    }
}
