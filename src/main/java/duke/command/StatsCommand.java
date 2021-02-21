package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class StatsCommand extends Command {

    private static final boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        String message = Ui.getStatsSuccess();
        return new CommandResponse(StatsCommand.class,
                message, StatsCommand.toExit);
    }

}
