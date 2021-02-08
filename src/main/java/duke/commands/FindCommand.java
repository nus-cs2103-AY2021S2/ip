package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert !query.isBlank() : "input should not be blank";
        assert !query.isEmpty() : "input should not be empty";

        TaskList printTaskList = new TaskList();
        for (Task task: taskList.getTaskList()) {
            if (task.getDescription().contains(query)) {
                printTaskList.add(task);
            }
        }

        if (printTaskList.size() <= 0) {
            throw new DukeException("No results found.");
        }

        return ui.showListMessage(printTaskList, true);
    }
}
