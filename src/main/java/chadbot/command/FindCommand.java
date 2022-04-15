package chadbot.command;

import chadbot.subfiles.TaskList;
import chadbot.subfiles.Ui;

/**
 * The FindCommand class is a command whose execution triggers the printing of tasks in the TaskList
 * containing a keyword specified by user input.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class FindCommand extends Command {

    /**
     * Default constructor for the FindCommand class.
     *
     * @param command The user input formatted as a String object.
     */
    public FindCommand(String command) {
        super(command);

        assert(command != null && !command.equals(""));
    }

    /**
     * Calls the task manager to print a list of tasks in the TaskList containing the specified keyword.
     *
     * @param taskList A TaskList object containing the list of tasks which the program currently has.
     * @param ui A Ui object which the current program is using to manage interactions with the user.
     * @return Duke's response to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return taskList.findTasksWithKeyword(command);
    }

    /**
     * Returns true if the command is an ExitCommand, and false otherwise.
     *
     * @return False, since this is not an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
