/**
 * Command to Exit.
 */
public class ExitCommand extends Command {

    private String command;

    /**
     * Constructs an exit command.
     *
     * @param command user input command.
     */
    public ExitCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the exit command by showing good bye message.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If exit command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIoException {
        return ui.showBye();
    }
}
