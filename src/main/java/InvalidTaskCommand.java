/**
 * Specifies the command for if a command type is not recognised.
 */
public class InvalidTaskCommand extends Command {

    /**
     * Initialises InvalidTaskCommand object.
     */
    public InvalidTaskCommand() {
    }

    /**
     * Executes the command by showing message that the task is not recognised.
     *
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showInvalidTaskMessage();
    }
}
