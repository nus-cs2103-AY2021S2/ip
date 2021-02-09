/**
 * Specifies the command for list command type.
 */
public class ListCommand extends Command {

    /**
     * Initialises ListCommand object.
     */
    public ListCommand() {
    }

    /**
     * Executes the command by responding with message that handles listing of the taskList.
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList.getList());
    }
}
