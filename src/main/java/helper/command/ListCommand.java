package helper.command;

import helper.Storage;
import helper.TaskList;
import helper.Ui;

/**
 * Command to list all tasks
 */
public class ListCommand extends Command {

    /**
     * Tell the UI to print tasks
     * @param tasks
     * @param ui
     * @param storage
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            return ui.listOfTaskToString(tasks.getTaskList());
        } catch (Exception e) {
            return "Nothing to list...";
        }
    }
}
