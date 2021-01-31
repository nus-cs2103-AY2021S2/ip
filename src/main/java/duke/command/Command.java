package duke.command;

import duke.subfiles.TaskList;
import duke.subfiles.Ui;

/**
 * The Command class is an abstract class which provides methods which
 * the other Command subclasses have to necessarily implement.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
 */
public abstract class Command {
    /** The user input formatted as a String object. */
    protected String command;

    /**
     * Default constructor for the Command class.
     *
     * @param command The user input formatted as a String object.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Executes an action, based on the type of the command.
     *
     * @param taskList A TaskList object containing the list of tasks
     *                 which the program currently has.
     * @param ui A Ui object which the current program is using to manage
     *           interactions with the user.
     */
    public abstract void execute(TaskList taskList, Ui ui);

    /**
     * Returns true if the command is an ExitCommand, and false otherwise.
     */
    public abstract boolean isExit();

}
