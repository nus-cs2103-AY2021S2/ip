package duke;

/**
 * Specifies the command for deadline command type.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Initialises the DoneCommand object.
     *
     * @param index the index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task as done in the existing taskList,
     * writing the updated taskList into storage and responding with relevant message.
     *
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     * @return Ui of response to user request.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            Task task = taskList.markTaskDone(index);
            storage.writeToFile(taskList.getList());
            return ui.showTaskDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This task number does not exist! Try again?");
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
