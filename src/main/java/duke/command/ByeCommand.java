package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a bye command keyed in by the user.
 */
public class ByeCommand extends Command {

    public ByeCommand(String[] parsedAction) {
        super(parsedAction);
    }

    /**
     * Executes the bye command by getting ui to print the goodbye message.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     * @return String string to output to user
     */
    public String execute(TaskManager taskManager, Ui ui, Storage storage) {
        return ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
