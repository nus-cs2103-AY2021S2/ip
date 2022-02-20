package yoda.command;

import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.ui.Ui;

/**
 * HelpCommand class that handles helping the user navigate the Yoda chatbot and
 * a child of the Command class.
 */
public class HelpCommand extends Command {
    /**
     * Creates a HelpCommand object.
     */
    public HelpCommand() {}

    /**
     * Shows list of commands available to user.
     * @param taskList TaskList associated with the command being executed.
     * @param ui Ui associated with the command being executed.
     * @param storage Storage associated with the command being executed.
     * @return Message showing all commands available to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
