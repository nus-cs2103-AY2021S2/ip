package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Finds and lists all tasks in task lists that contains any of the argument keywords.
 */
public class FindCommand extends Command {

    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Retrieves all tasks in the task lists that contains any of the argument keywords.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @param storage storage object
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList printTaskList = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if (task.getDescription().contains(query)) {
                printTaskList.add(task);
            }
        }
        return ui.showListMessage(printTaskList, true);
    }
}
