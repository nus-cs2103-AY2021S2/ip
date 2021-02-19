package helper.command;

import helper.Storage;
import helper.TaskList;
import helper.Ui;

/**
 * Command to end duke
 */
public class ExitCommand extends Command {
    /**
     * Tells duke to exit
     * @param tasks
     * @param ui
     * @param storage
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.isExit = true;
        String stringToReturn = ("Bye!!!");
        return stringToReturn;
    }

}
