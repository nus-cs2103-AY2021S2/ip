package chadbot.command;

import chadbot.subfiles.TaskList;
import chadbot.subfiles.Ui;

/**
 * The StatsCommand class is a command whose execution triggers the output of the current number of tasks
 * the user has in his or her task list.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class StatsCommand extends Command {

    /**
     * Default constructor for the StatsCommand class.
     */
    public StatsCommand() {
        super("");
    }

    /**
     * Calls the task manager to output the number of to-dos, deadlines, and events that the user currently has.
     *
     * @param taskList A TaskList object containing the list of tasks which the program currently has.
     * @param ui A Ui object which the current program is using to manage interactions with the user.
     * @return Duke's response to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String output = taskList.displayStats();
        System.out.println(output);
        return output;
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
