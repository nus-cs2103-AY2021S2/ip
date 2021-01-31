import java.io.IOException;

/**
 * A command that represents marking a <code>Task</code> in a <code>TaskList</code>
 */
public class DoneCommand extends Command {

    int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the done command and marks the task as done from the <code>TaskList</code>.
     * <code>Ui</code> prints the task marked.
     * <code>Storage</code> helps to mark the task in the text file.
     *
     * @param tasklist contains the task list and operations to manipulate the list
     * @param ui       deals with interactions with the user
     * @param storage  deals with loading tasks from the file and saving tasks in the file
     * @throws IOException   is thrown when there is an error related to input and output
     * @throws DukeException is thrown when there is an error related to duke
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException, DukeException {
        tasklist.markTask(taskIndex);
        ui.showMarkTask(tasklist.getList().get(taskIndex));
        storage.writeToFile(tasklist);
    }
}
