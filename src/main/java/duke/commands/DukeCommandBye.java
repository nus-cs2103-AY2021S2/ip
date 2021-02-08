package duke.commands;

import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Exit command.
 *
 * Allows main loop to terminate program.
 */
public class DukeCommandBye extends DukeCommand {

    /** Passes instruction to main loop to terminate program.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints exit message.
     *
     * @param tasks tasklist
     * @param ui user interface
     * @param loader loader
     */
    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }
}
