/**
 * Represents a user command.
 */


public abstract class Command {
    protected final String fullCommand;
    protected final String typeOfCommand;

    /**
     * Constructor that takes in two parameters, <code>fullCommand</code> and <code>typeOfCommand</code>.
     * @param fullCommand the full user input
     * @param typeOfCommand the type of command to be executed
     */
    public Command(String fullCommand, String typeOfCommand) {
        this.fullCommand = fullCommand;
        this.typeOfCommand = typeOfCommand;
    }

    /**
     * Carries out the desired command taken from the user on the task list and
     * returns a response to display to the user
     * @param tasks the task list that the command must be carried out on
     * @return a response to be displayed to the user after executing the command
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks) throws DukeException;
}
