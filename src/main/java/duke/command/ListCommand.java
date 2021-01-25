package duke.command;

/**
 * Create list command class
 */
import duke.data.DataStorage;
import duke.TaskList.TaskList;
import duke.UI.UI;

public class ListCommand extends Command {

    /**
     * Constructor to create delete command object
     */

    public ListCommand(String input, TaskList tl) {
        super(input,tl);
    }

    /** Display all the task found in list
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public TaskList execute(TaskList tasks, UI ui, DataStorage storage){
        tasks.showAllTask();
        return null;
    }

}
