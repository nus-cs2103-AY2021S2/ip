package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Creates a new instance of <code>ExitCommand</code>.
     * <code>isExit</code> is set to true.
     */
    public ExitCommand() {
        this.type = "bye";
        this.description = "";
        this.isExit = true;
    }

    /**
     * Terminates Duke.
     *
     * @param tasks Task List,
     * @param ui User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
