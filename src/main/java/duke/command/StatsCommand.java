package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
