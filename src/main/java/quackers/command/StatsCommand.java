package quackers.command;

import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

/**
 * Represents the command show statistics.
 */
public class StatsCommand extends Command {

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
        String message = Ui.getStatsSuccess();
        return new CommandResponse(StatsCommand.class, message, StatsCommand.toExit);
    }

}
