package chadbot.command;

import chadbot.exceptions.DateFormatException;
import chadbot.subfiles.TaskList;
import chadbot.subfiles.Ui;

/**
 * The PrintCommand class is a command whose execution triggers the printing of tasks in the TaskList,
 * based on the user input.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class PrintCommand extends Command {

    /**
     * Default constructor for the PrintCommand class.
     *
     * @param command The user input formatted as a String object.
     */
    public PrintCommand(String command) {
        super(command);

        assert(command != null && !command.equals(""));
    }

    /**
     * Calls the task manager to either print the entire list of tasks in the TaskList,
     * or the deadlines and events that are due or happening on the specified date.
     *
     * @param taskList A TaskList object containing the list of tasks which the program currently has.
     * @param ui A Ui object which the current program is using to manage interactions with the user.
     * @return Duke's response to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String[] sArray = command.split(" ");

        if (sArray.length == 1) {
            return taskList.printTasks();
        } else {
            try {
                return taskList.printTasksOnDate(sArray[1]);
            } catch (DateFormatException e) {
                ui.showError(e.getMessage());
                return e.getMessage();
            }
        }
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
