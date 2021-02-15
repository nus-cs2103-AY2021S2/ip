import java.util.ArrayList;

/**
 * Specifies the command for find command type.
 */
public class FindCommand extends Command {

    /**
     * Initialises FindCommand object.
     *
     * @param description
     */
    public FindCommand(String description) {
        super(description);
    }

    /**
     * Executes the command by finding a list of tasks that contain the keyword and
     * responding with message that lists the matching tasks.
     *
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.findTasks(description);
        ui.showMatchingTasks(matchingTasks);
    }
}
