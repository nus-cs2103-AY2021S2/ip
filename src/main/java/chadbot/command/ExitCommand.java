package chadbot.command;

import chadbot.subfiles.TaskList;
import chadbot.subfiles.Ui;

/**
 * The ExitCommand class is a command whose execution triggers the Duke program to terminate its execution.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class ExitCommand extends Command {

    /**
     * Default constructor for the ExitCommand class.
     */
    public ExitCommand() {
        super("");
    }

    /**
     * Does nothing.
     *
     * @param taskList A TaskList object containing the list of tasks which the program currently has.
     * @param ui A Ui object which the current program is using to manage interactions with the user.
     * @return An empty response by Duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return "Bye :)";
    }

    /**
     * Returns true if the command is an ExitCommand, and false otherwise.
     *
     * @return True, since this is the ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
