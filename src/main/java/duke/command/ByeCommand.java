package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    private static final boolean toExit = true;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        String message = Ui.getByeSuccess();
        return new CommandResponse(ByeCommand.class, message, ByeCommand.toExit);
    }
}
