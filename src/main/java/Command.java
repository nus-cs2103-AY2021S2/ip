/**
 * Abstract command parent class.
 */
public abstract class Command {
    /**
     * Abstract execute method.
     * Each command has a execute method that when called, executes that particular command.
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If todo, deadline or event is missing detail description.
     * @throws DukeWrongInputException If user input is not any of the commands available.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException;

    /**
     * Abstract method that indicates if particular method is an exit command.
     * @return boolean value for whether the task is an exit command.
     */
    public abstract boolean isExit();
}
