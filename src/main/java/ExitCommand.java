/**
 * Specifies the command for deadline command type.
 */
public class ExitCommand extends Command {

    /**
     * Initialises ExitCommand object.
     */
    public ExitCommand() {
    }

    /**
     * Executes the command by writing the taskList into storage, showing goodbye message and exiting
     * the program.
     *
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.writeToFile(taskList.getList());
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
