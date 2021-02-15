package duke;

/**
 * Specifies the command for deadline command type.
 */
public class DoneCommand extends Command {

    /**
     * Initialises the DoneCommand object.
     *
     * @param index the index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        super(index);
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.markTaskDone(index);
        storage.writeToFile(taskList.getList());
        return ui.showTaskDone(task);
    }
}
