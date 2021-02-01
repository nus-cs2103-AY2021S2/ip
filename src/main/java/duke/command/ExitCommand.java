package duke.command;

import duke.subfiles.TaskList;
import duke.subfiles.Ui;

/**
 * The ExitCommand class is a command whose execution triggers the
 * Duke program to terminate its execution.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
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
     * @param taskList A TaskList object containing the list of tasks
     *                 which the program currently has.
     * @param ui A Ui object which the current program is using to manage
     *           interactions with the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return "";
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
