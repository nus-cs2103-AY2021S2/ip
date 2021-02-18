package duke;

public class UpdateCommand extends Command {
    private int index;
    private String newDescription;

    public UpdateCommand(String index, String newDescription) {
        this.index = Integer.parseInt(index) - 1;
        this.newDescription = newDescription;
    }

    /**
     *
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.updateTask(index, newDescription);
            storage.writeToFile(taskList.getList());
            return ui.showTaskUpdated(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This task number does not exist! Try again?");
        }
    }
}
