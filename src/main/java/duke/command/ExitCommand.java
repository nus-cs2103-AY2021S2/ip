package duke.command;

import duke.data.DataStorage;
import duke.exception.DukeException;
import duke.TaskList.TaskList;
import duke.UI.UI;

/**
 * Create exit command class
 */
public class ExitCommand extends Command {

    /**
     * Constructor to create exit command object
     */
    public ExitCommand(){
        super(null,null);
        this.isExit = true;
    }

    /** Terminate the whole program
     * @param tasklist
     * @param ui
     * @param storage
     * @return
     * @throws DukeException
     */
    @Override
    public TaskList execute(TaskList tasklist, UI ui, DataStorage storage) throws DukeException {
        ui.displayEndMessage();
        return null;
    }
}
