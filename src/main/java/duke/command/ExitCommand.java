package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * ExitCommand represent a command to exit from the program.
 */
class ExitCommand extends Command{

    /**
     * Returns a ExitCommand object to exit from the program.
     */
    ExitCommand() {
        super(null, null, null,null, true);
    }

    /**
     * Outputs a goodbye message.
     *
     * @param tasks TaskList storing the current tasks.
     * @param ui The user interface of the program.
     * @param storage The storage of the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}
