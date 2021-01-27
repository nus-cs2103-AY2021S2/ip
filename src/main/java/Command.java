/**
 * Represents a user command.
 */


public abstract class Command {
    protected final String fullCommand;
    protected final String action;

    /**
     * Constructor takes in a <code>fullCommand</code>, the full user input
     * consisting of the desired command, and a <code>action</code> which
     * states the type of command to be executed
     * @param fullCommand the full user input
     * @param action the type of command to be executed
     */

    public Command(String fullCommand, String action) {
        this.fullCommand = fullCommand;
        this.action = action;
    }

    /**
     * A method that carries out the desired command taken from the user
     * on the task list
     * @param tasks the task list that the command must be carried out on
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks) throws DukeException;
}
