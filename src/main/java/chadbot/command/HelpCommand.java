package chadbot.command;

import chadbot.subfiles.TaskList;
import chadbot.subfiles.Ui;

/**
 * The HelpCommand class is a command whose execution triggers the display of a help page to users.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class HelpCommand extends Command {

    /**
     * Default constructor for the HelpCommand class.
     */
    public HelpCommand(String command) {
        super(command);

        assert(command != null && !command.equals(""));
    }

    /**
     * Calls the task manager to display a help page containing a list of commands which the user could execute.
     *
     * @param taskList A TaskList object containing the list of tasks which the program currently has.
     * @param ui A Ui object which the current program is using to manage interactions with the user.
     * @return Duke's response to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String output = command.toLowerCase().contains("/more")
                ? ui.getMoreHelp()
                : ui.getHelp();
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
