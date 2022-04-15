import java.io.IOException;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {

    private String command;

    /**
     * Constructs a delete command.
     *
     * @param command command input from user.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the delete command.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If delete command is missing description.
     * @throws DukeWrongInputException If user input is wrong format.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIoException {
        String[] commandArr = command.split(" ");
        if (commandArr.length == 1) {
            throw new DukeMissingInputException("Oops! Missing arguments.");
        }
        if (!isNumeric(commandArr[1])) {
            throw new DukeWrongInputException("Oops! Please provide a valid task number to delete.");
        }
        int itemPosition = Integer.parseInt(commandArr[1]) - 1;
        if (itemPosition + 1 > taskList.getTaskListLength() || itemPosition < 0) {
            throw new DukeWrongInputException("Oops! Item number to be deleted out of bounds");
        }

        try {
            String output = ui.showTaskDeleted(taskList.getTaskAtIndex(itemPosition));
            taskList.delete(itemPosition);
            storage.save(taskList.getTaskList());
            return output;
        } catch (IOException e) {
            throw new DukeIoException("File error: Could not save.");
        }
    }

    /**
     * Checks if a string is numeric.
     *
     * @param string input string.
     * @return false if a string is not numeric. True otherwise.
     */
    public boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            Integer number = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
