/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {

    private String command;

    /**
     * Constructor method.
     * @param command command input from user.
     */
    public DeleteCommand(String command){
        this.command = command;
    }

    /**
     * Execute command for delete command.
     * @param taskList List of Tasks.
     * @param ui Standard UI object.
     * @param storage Standard storage object.
     * @throws DukeMissingInputException If delete command is missing description.
     * @throws DukeWrongInputException If user input is not any of the inputs available.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException, DukeWrongInputException {
        String[] commandArr = command.trim().split(" ");
        ui.showTaskDeleted(taskList.getTaskAtIndex(Integer.parseInt(commandArr[1]) - 1));
        taskList.delete(Integer.parseInt(commandArr[1]) - 1);
    }

    /**
     * Indicates whether command is an exit command.
     * @return boolean value for whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}