import java.io.IOException;

/**
 * Command to add a task.
 * Inherits from the superclass Command.
 */
public class AddCommand extends Command {
    private final Task toAdd;

    /**
     * Constructor to keep track of the Task being added.
     * @param toAdd The task to be added.
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Adds the task into the TaskList and update the file in hard drive respectively.
     * Print messages to inform user that the task has been added.
     * @param taskList TaskList that contains all tasks at hand.
     * @param ui Ui that deals with interaction with the user.
     * @param storage Storage that deals with storing TaskList into hard drive.
     * @throws IOException If there is an error while updating the file in hard drive.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(this.toAdd);
        storage.update(taskList);
        return ui.printAddTaskMessage(this.toAdd, taskList);
    }
}
