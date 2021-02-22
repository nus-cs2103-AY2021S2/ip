package quackers.command;

import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

/**
 * Represents the command to exit program.
 */
public class ByeCommand extends Command {

    private static final boolean toExit = true;

    /**
     * Executes the command and returns a response.
     *
     * @param tasks Core TaskList object.
     * @param storage Core Storage object.
     * @return CommandResponse object.
     */
    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) {
        String message = Ui.getByeSuccess();
        return new CommandResponse(ByeCommand.class, message, ByeCommand.toExit);
    }
}
