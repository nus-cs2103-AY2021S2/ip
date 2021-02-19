package duke;

/**
 * Specifies the command for todo command type.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Initialises the TodoCommand object.
     *
     * @param description the description of the deadline task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by adding the todo task to the existing taskList,
     * writing the updated taskList into storage and responding with relevant message.
     *
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui       the Ui object that provides responses to the user according to status of their input.
     * @param storage  the Storage object that contains the tasks saved in computer's hard disk.
     * @return Ui of response to user request.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            Todo todo = new Todo(description);
            taskList.addTodoTask(todo);
            storage.writeToFile(taskList.getList());
            return ui.showTaskAdded(todo);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
