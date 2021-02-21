package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

public class StatsCommand extends Command {

    private static final boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        String message = Ui.getStatsSuccess();
        return new CommandResponse(StatsCommand.class,
                message, StatsCommand.toExit);
    }

}
