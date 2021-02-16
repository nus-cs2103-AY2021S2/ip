/**
 * Abstract command parent class.
 */
public abstract class Command {
    /**
     * Executes the respective commands.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If todo, deadline or event is missing detail description.
     * @throws DukeWrongInputException If user input is not any of the commands available.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIoException;
}
