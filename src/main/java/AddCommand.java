import java.io.IOException;

/**
 * A command that represents adding a <code>Task</code> into a <code>TaskList</code>
 */
public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the adding command and adds the task to the TaskList.
     * <code>Ui</code> prints the task added
     * and the storage stores the task into the text file.
     *
     * @param tasklist contains the task list and operations to manipulate the list
     * @param ui       deals with interactions with the user
     * @param storage  deals with loading tasks from the file and saving tasks in the file
     * @return String that consists of a message for added task
     * @throws IOException is thrown when there is an error related to input and output
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        String message;
        tasklist.addTask(task);
        message = ui.showAddTask(tasklist);
        storage.writeToFile(tasklist);

        return message;
    }
}
