/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {

    private String command;

    /**
     * Constructor method.
     *
     * @param command command input from user.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Execute command for delete command.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If delete command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException {
        String[] commandArr = command.trim().split(" ");
        int itemPosition = Integer.parseInt(commandArr[1]) - 1;
        if (itemPosition + 1 <= taskList.getTaskListLength() && itemPosition >= 0) {
            String output = ui.showTaskDeleted(taskList.getTaskAtIndex(itemPosition));
            taskList.delete(itemPosition);
            storage.save(taskList.getTaskList());
            return output;
        } else {
            throw new DukeWrongInputException("Oops! Item number to be deleted out of bounds");
        }
    }

    /**
     * Indicates whether command is an exit command.
     *
     * @return boolean value for whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
