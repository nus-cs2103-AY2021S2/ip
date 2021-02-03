/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {

    private String command;

    public DoneCommand(String command) {
        this.command = command;
    }

    /**
     * Overriding execute method.
     *
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If done command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException,
            DukeWrongInputException {
        String[] commandArr = command.trim().split(" ");
        int itemPosition = Integer.parseInt(commandArr[1]) - 1;
        if (itemPosition + 1 <= taskList.getTaskListLength() && itemPosition >= 0) {
            Task doneTask = taskList.getTaskAtIndex(itemPosition);
            doneTask.markAsDone();
            storage.save(taskList.getTaskList());
            return ui.showTaskDone(doneTask);
        } else {
            throw new DukeWrongInputException("Oops! Item number to be marked done is out of bounds.");
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
