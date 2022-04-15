package duke;

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
     * Executes the command by writing the taskList into storage and showing goodbye message.
     *
     * @param ui      the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     * @return Ui of response to user request.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            storage.writeToFile(taskList.getList());
            return ui.showGoodbyeMessage();
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
