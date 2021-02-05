package percy.command;

import java.util.ArrayList;
import java.util.List;

import percy.storage.Storage;
import percy.task.Task;
import percy.task.TaskList;
import percy.ui.Ui;


/**
 * Finds all tasks in the task list with the keyboard.
 */
public class FindCommand extends Command {
    public static final String COMMAND = "find";
    public static final ArrayList<String> USAGE_GUIDE = new ArrayList<String>(List.of(
            "find: Finds a task by searching for a keyword.",
            "Parameters: KEYWORD",
            "Example: find book"));
    private final String[] keywords;

    /**
     * Constructs find command.
     */
    public FindCommand(String... keyword) {
        super(false);
        this.keywords = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.findTask(keywords);
        TaskList filteredList = new TaskList(filteredTasks);
        return Ui.makeSearchResultsMsg(filteredList);
    }
}
