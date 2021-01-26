package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;

/**
 * Create exit command class
 */
public class ExitCommand extends Command {

    /**
     * Constructor to create exit command object
     */
    public ExitCommand() {
        super(null, null);
        this.isExit = true;
    }

    /** Terminate the whole program
     * @param tasklist
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public TaskList execute(TaskList tasklist, UI ui, DataStorage storage) {
        ui.displayEndMessage();
        return null;
    }
}
