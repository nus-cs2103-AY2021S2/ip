/**
 * Command to find the list of task containing the particular keyword.
 */
public class FindCommand extends Command {

    private String command;

    /**
     * Constructs the find command.
     *
     * @param command user input containing keyword to search for.
     */
    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the find command by displaying the list of tasks found based on keyword.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @return the goodbye message shown by UI.
     * @throws DukeMissingInputException If find command is missing keyword.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIoException {
        String[] commandArr = command.split(" ");
        if (commandArr.length == 1) {
            throw new DukeMissingInputException("Oops! Missing keyword.");
        }
        return ui.showFoundListItems(taskList, command);
    }
}
