package yoda.command;

import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.ui.Ui;

/**
 * ErrorCommand class that handles invalid commands entered by user and a child of Command class.
 */
public class ErrorCommand extends Command {
    /**
     * Creates an ErrorCommand object.
     */
    public ErrorCommand() {}

    /**
     * Shows user that they have entered an invalid command.
     * @param taskList TaskList associated with the command being executed.
     * @param ui Ui associated with the command being executed.
     * @param storage Storage associated with the command being executed.
     * @return Message informing an invalid command was entered and prompting user to use help command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showError();
    }
}
