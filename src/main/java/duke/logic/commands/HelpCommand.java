package duke.logic.commands;

import duke.model.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Shows list of commands that Duke can support.
 */
public class HelpCommand extends Command {
    /**
     * Prints to the user of the list of commands that can be executed.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @param storage storage object
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) {
        return new CommandResponse(ui.showHelpMessage(), false);
    }
}
