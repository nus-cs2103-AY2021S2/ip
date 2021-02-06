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
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Exits the Yoda chatbot after writing TaskList to the file.
     * @param taskList TaskList associated with the ExitCommand being executed.
     * @param ui Ui associated with the ExitCommand being executed.
     * @param storage Storage associated with the ExitCommand being executed.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.write(taskList);
        ui.exit();
    }
}
