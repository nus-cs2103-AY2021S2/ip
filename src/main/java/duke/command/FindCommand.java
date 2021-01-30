package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    @Override
    public void execute(String searchString, Task task, TaskList taskList, Storage storage) {
        // filter tasks that contains the search string
        TaskList searchResult = taskList.filterTasks(searchString);

        // print the search result
        Ui.printMessage("Here are the matching tasks in your list:\n"
                + searchResult.toString());
    }
}
