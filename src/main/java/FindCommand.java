import java.io.IOException;

/**
 * Command to find a Task based on a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor to keep track of the keyword used.
     * @param keyword Used to find a task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Goes through the TaskList to search for related Tasks.
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.printSomeTasks(taskList.findTasks(this.keyword));
    }
}
