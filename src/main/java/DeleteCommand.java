import java.io.IOException;

/**
 * A command that represents deleting a <code>Task</code> from a <code>TaskList</code>
 */
public class DeleteCommand extends Command {

    int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command and removes the task from the <code>TaskList</code>.
     * <code>Ui</code> prints the task deleted.
     * <code>Storage</code> helps the task to be deleted from the text file.
     *
     * @param tasklist contains the task list and operations to manipulate the list
     * @param ui       deals with interactions with the user
     * @param storage  deals with loading tasks from the file and saving tasks in the file
     * @return String that consists of deleted task
     * @throws IOException   is thrown when there is an error related to input and output
     * @throws DukeException is thrown when there is an error related to duke
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws IOException, DukeException {
        String message;
        message = ui.showDeleteTask(tasklist, taskIndex);
        tasklist.deleteTask(taskIndex);
        storage.writeToFile(tasklist);

        return message;
    }
}
