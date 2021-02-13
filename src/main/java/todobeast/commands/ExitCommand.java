package todobeast.commands;

import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.ToDoBeastException;

/**
 * A Command that indicates intention to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * The isExit property of this command will be set to true, which will trigger the exit process of the application.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Filler method that does not contain any actual logic.
     * @param taskList the TaskList that belongs to this application
     * @param ui the Ui that belongs to this application
     * @throws ToDoBeastException
     */
    public void execute(TaskList taskList, Ui ui) {
    }
}
