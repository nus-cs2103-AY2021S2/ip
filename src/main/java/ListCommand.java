/**
 * Command for showing the entire list of tasks.
 */
public class ListCommand extends Command {

    private String command;

    /**
     * Constructor method
     * @param command user command input
     */
    public ListCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the list command to show the full list of tasks.
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If list command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIOException {
        return ui.showListItems(taskList) + "\n" + ui.showNumberOfItems(taskList.getTaskListLength());
    }
}
