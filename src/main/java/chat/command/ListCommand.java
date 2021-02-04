package chat.command;

import chat.TaskList;
import chat.Storage;
import chat.Ui;

/**
 * A type of command that shows the user all tasks in list of tasks
 * from TaskList object.
 */

public class ListCommand extends Command {

    /**
     * Method that lists all tasks and their details from list of tasks from TaskList object
     * @param taskList TaskList object that contains a list of tasks.
     * @param ui Ui object that gives responses to user.
     * @param storage Storage object that interacts with task data in hard disk.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.list(taskList);
    }
    
}
