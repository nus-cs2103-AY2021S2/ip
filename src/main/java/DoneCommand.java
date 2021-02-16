import java.io.IOException;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {

    private String command;

    /**
     * Constructs a done command.
     *
     * @param command user command.
     */
    public DoneCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the done command by marking a particular task as done.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If done command is missing number description.
     * @throws DukeWrongInputException If user input is invalid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException, DukeIoException {
        String[] commandArr = command.trim().split(" ");
        if (commandArr.length == 1) {
            throw new DukeMissingInputException("Oops! Missing arguments.");
        }
        if (!isNumeric(commandArr[1])) {
            throw new DukeWrongInputException("Oops! Please provide a valid task number to mark as done.");
        }
        int itemPosition = Integer.parseInt(commandArr[1]) - 1;
        if (itemPosition + 1 > taskList.getTaskListLength() || itemPosition < 0) {
            throw new DukeWrongInputException("Oops! Item number to be marked as done is out of bounds");
        }
        try {
            Task doneTask = taskList.getTaskAtIndex(itemPosition);
            doneTask.markAsDone();
            storage.save(taskList.getTaskList());
            return ui.showTaskDone(doneTask);
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
