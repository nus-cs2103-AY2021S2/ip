package chadbot.command;

import chadbot.subfiles.TaskList;
import chadbot.subfiles.Ui;

/**
 * The Command class is an abstract class which provides methods which the other Command subclasses
 * have to necessarily implement.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
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
     * @param taskList A TaskList object containing the list of tasks which the program currently has.
     * @param ui A Ui object which the current program is using to manage interactions with the user.
     * @return Duke's response to the user.
     */
    public abstract String execute(TaskList taskList, Ui ui);

    /**
     * Returns true if the command is an ExitCommand, and false otherwise.
     */
    public abstract boolean isExit();

}
