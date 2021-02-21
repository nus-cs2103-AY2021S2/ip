package yoda.command;

import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.ui.Ui;

/**
 * ExitCommand class that handles exiting the Yoda chatbot after the user is done using and
 * a child of the Command class.
 */
public class ExitCommand extends Command {
    /**
     * Creates an ExitCommand object.
     */
    public ExitCommand() {}

    /**
     * Exits the Yoda chatbot after writing tasklist to the file.
     * @param taskList TaskList associated with the command being executed.
     * @param ui Ui associated with the command being executed.
     * @param storage Storage associated with the command being executed.
     * @return Message saying goodbye to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.serialize(taskList);
        return ui.exit();
    }
}
