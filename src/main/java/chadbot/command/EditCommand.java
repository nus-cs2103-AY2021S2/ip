package chadbot.command;

import chadbot.exceptions.DateFormatException;
import chadbot.exceptions.InvalidInputException;
import chadbot.exceptions.ListOutOfBoundsException;
import chadbot.exceptions.TaskTypeErrorException;
import chadbot.subfiles.TaskList;
import chadbot.subfiles.Ui;

/**
 * The EditCommand class is a command whose execution triggers the editing of a task in the TaskList,
 * based on the user input.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class EditCommand extends Command {

    /**
     * Default constructor for the EditCommand class.
     *
     * @param command The user input formatted as a String object.
     */
    public EditCommand(String command) {
        super(command);

        assert(command != null && !command.equals(""));
    }

    /**
     * Calls the task manager to edit a specified task.
     *
     * @param taskList A TaskList object containing the list of tasks which the program currently has.
     * @param ui A Ui object which the current program is using to manage interactions with the user.
     * @return Duke's response to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        try {
            return taskList.editTask(command);
        } catch (InvalidInputException | ListOutOfBoundsException | TaskTypeErrorException | DateFormatException e) {
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
