package chadbot.command;

import chadbot.subfiles.TaskList;
import chadbot.subfiles.Ui;

/**
 * The SortCommand class is a command whose execution triggers the sorting of the TaskList,
 * the type of sorting done is based on the user input.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class SortCommand extends Command {

    /**
     * Default constructor for the SortCommand class.
     *
     * @param command The user input formatted as a String object.
     */
    public SortCommand(String command) {
        super(command);

        assert(command != null && !command.equals(""));
    }

    /**
     * Calls the task manager to sort the task list upon receiving a user input that attempts to sort the list.
     * The default mode of sorting is by alphabetical ordering of the tasks.
     *
     * @param taskList A TaskList object containing the list of tasks which the program currently has.
     * @param ui A Ui object which the current program is using to manage interactions with the user.
     * @return Duke's response to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        if (command.contains("date")) {
            taskList.sortTasksByTypeAndDate();
        } else if (command.contains("type")) {
            taskList.sortTasksByType();
        } else {
            taskList.sortTasksByName();
        }

        String output = "Tasks sorted!";
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
