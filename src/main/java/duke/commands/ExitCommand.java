package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Responsible for stopping the program.
 */
public class ExitCommand extends Command {
    /**
     * Returns goodbye message before stopping the program.
     *
     * @param tasks TaskList containing tasks.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @return Goodbye message string.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getByeMessage();
    }
}
