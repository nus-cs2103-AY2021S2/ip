package chadbot.command;

import chadbot.exceptions.DateFormatException;
import chadbot.exceptions.EmptyDateException;
import chadbot.exceptions.EmptyDescriptionException;
import chadbot.exceptions.InvalidInputException;
import chadbot.subfiles.TaskList;
import chadbot.subfiles.Ui;

/**
 * The AddCommand class is a command whose execution triggers the addition of a task into the TaskList,
 * based on the user input.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class AddCommand extends Command {

    /**
     * Default constructor for the AddCommand class.
     *
     * @param command The user input formatted as a String object.
     */
    public AddCommand(String command) {
        super(command);
    }

    /**
     * Calls the task manager to add a specified task to the list upon receiving a user input that attempts to
     * add a task to the list.
     *
     * @param taskList A TaskList object containing the list of tasks which the program currently has.
     * @param ui A Ui object which the current program is using to manage interactions with the user.
     * @return Duke's response to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        try {
            return taskList.addTask(command);
        } catch (EmptyDescriptionException | EmptyDateException | InvalidInputException
                | DateFormatException e) {
            ui.showError(e.getMessage());
            return e.getMessage();
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
