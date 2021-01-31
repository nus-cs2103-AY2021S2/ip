package helper.command;

import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Task;

import java.util.List;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.dukePrint(tasks.getTaskList());
        } catch (Exception e) {
            ui.dukePrint("Nothing to list...");
        }
    }
}
