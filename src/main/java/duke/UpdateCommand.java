package duke;

/**
 * Specifies the command for update command type.
 */
public class UpdateCommand extends Command {
    private int index;
    private String newDescription;

    /**
     * Initialise UpdateCommand object and parses description to index of task and description.
     *
     * @param newDescription new description to be updated, including index of task.
     */
    public UpdateCommand(String newDescription) throws DukeException {
        String[] descriptionArr = newDescription.split(" ", 2);
        try {
            this.index = Integer.parseInt(descriptionArr[0]) - 1;
            this.newDescription = descriptionArr[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please describe what you want to update!");
        }
    }

    /**
     * Executes the command by updating existing task in the taskList,
     * writing the updated taskList into storage and responding with relevant message.
     *
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     * @return Ui of response to user request.
     * @throws DukeException if the specified index of task does not exist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            Task task = taskList.updateTask(index, newDescription);
            storage.writeToFile(taskList.getList());
            return ui.showTaskUpdated(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This task number does not exist! Try again?");
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
