/**
 * Represents a Command.
 */
public abstract class Command {

    /**
     * Executes the command given by the user.
     * @param tasks List of tasks user has saved
     * @param ui Handles interaction with user.
     * @param storage Handles saving tasks to computer.
     */
    abstract String execute(TaskList tasks,Ui ui, Storage storage);
}
