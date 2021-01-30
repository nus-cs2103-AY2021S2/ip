package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Responsible for stopping the program.
 */
public class ExitCommand extends Command {
    /**
     * Constructs a ExitCommand object.
     */
    public ExitCommand() {}

    /**
     * Prints goodbye message before stopping the program.
     *
     * @param tasks TaskList containing tasks.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

    /**
     * Returns if program should exit after this command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
