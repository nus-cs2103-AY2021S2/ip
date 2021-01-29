package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;

/**
 * Create list command class
 */
public class ListCommand extends Command {

    /**
     * Constructor to create delete command object
     */

    public ListCommand(String input, TaskList tl) {
        super(input, tl);
    }

    /** Display all the task found in list
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public TaskList execute(TaskList tasks, UI ui, DataStorage storage) {
        tasks.showAllTask("list");
        return null;
    }

}
