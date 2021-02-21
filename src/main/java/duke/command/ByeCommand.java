package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    private static final boolean toExit = true;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        String message = Ui.getByeSuccess();
        return new CommandResponse(ByeCommand.class, message, ByeCommand.toExit);
    }
}
