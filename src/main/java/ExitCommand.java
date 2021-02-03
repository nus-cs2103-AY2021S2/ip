/**
 * Command to create an Event.
 */
public class ExitCommand extends Command {

    private String command;

    /**
     * Constructor method
     * @param command user input command
     */
    public ExitCommand(String command) {
        this.command = command;
    }

    /**
     * Dummy Execute method for Exit Command
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If exit command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException {
        return ui.showBye();
    }

    /**
     * Indicates whether command is an exit command.
     * @return boolean value for whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
