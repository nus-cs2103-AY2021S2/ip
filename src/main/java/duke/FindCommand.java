package duke;

import java.util.ArrayList;

/**
 * Specifies the command for find command type.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Initialises FindCommand object.
     *
     * @param keyword refers to the keyword provided by user
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by finding a list of tasks that contain the keyword and
     * responding with message that lists the matching tasks.
     *
     * @param taskList the TaskList object that contains all tasks added by user.
     * @param ui the Ui object that provides responses to the user according to status of their input.
     * @param storage the Storage object that contains the tasks saved in computer's hard disk.
     * @return Ui of response to user request.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }
}
