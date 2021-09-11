/**
 * Command to find a Task based on a keyword.
 */
public class FindCommand extends Command {
    private final String[] keywords;

    /**
     * Constructor to keep track of the keyword used.
     * @param input Used to find a task.
     */
    public FindCommand(String ... input) {
        this.keywords = input;
    }

    /**
     * Goes through the TaskList to search for related Tasks.
     * @param taskList TaskList that contains all tasks at hand.
     * @param ui Ui that deals with interaction with the user.
     * @param storage Storage that deals with storing TaskList into hard drive.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printSomeTasks(taskList.findTasks(this.keywords));
    }
}
