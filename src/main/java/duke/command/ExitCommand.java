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
        super("exit");
    }

    /** Terminate the whole program
     * @return
     */
    @Override
    public String execute() {
        return ui.displayEndMessage();
    }
}
