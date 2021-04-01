package quackers.command;

import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

/**
 * Represents the command to view commands usage.
 */
public class UsageCommand extends Command {

    private static final boolean toExit = false;

    /**
     * Executes the command and returns a response.
     *
     * @param tasks Core TaskList object.
     * @param storage Core Storage object.
     * @return CommandResponse object.
     */
    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) {
        String message = Ui.getUsage();
        return new CommandResponse(UsageCommand.class, message, UsageCommand.toExit);
    }
}
