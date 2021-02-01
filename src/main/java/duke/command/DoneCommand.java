package duke.command;

import duke.exceptions.InvalidInputException;
import duke.exceptions.ListOutOfBoundsException;
import duke.subfiles.TaskList;
import duke.subfiles.Ui;

/**
 * The DoneCommand class is a command whose execution triggers the
 * marking of a task in the TaskList as done, based on the user input.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
 */
public class DoneCommand extends Command {

    /**
     * Default constructor for the DoneCommand class.
     *
     * @param command The user input formatted as a String object.
     */
    public DoneCommand(String command) {
        super(command);
    }

    /**
     * Calls the task manager to mark a specified task as done
     * upon receiving a user input that attempts to mark a task
     * as done.
     *
     * @param taskList A TaskList object containing the list of tasks
     *                 which the program currently has.
     * @param ui A Ui object which the current program is using to manage
     *           interactions with the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        try {
            return taskList.markDone(command);
        } catch (InvalidInputException | ListOutOfBoundsException e) {
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
