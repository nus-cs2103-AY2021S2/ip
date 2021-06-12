package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

/**
 * Command for find input
 */
public class FindCommand extends Command {

    /**
     * Filters search string in task list and prints all matching tasks
     * @param searchString the description to search for
     * @param task
     * @param taskList the current instance of task list used by Duke
     * @param storage
     */
    @Override
    public String execute(String searchString, Task task, TaskList taskList, Storage storage) {
        // filter tasks that contains the search string
        List<Task> searchResult = taskList.filterTasks(searchString);

        // print the search result
        return Ui.matchingList(searchResult);
    }
}
