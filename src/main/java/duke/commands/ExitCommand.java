package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    /**
     * Terminates the program and prints a farewell message to the user.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @param storage storage object
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) {
        return new CommandResponse(ui.showExit(), true);
    }
}
