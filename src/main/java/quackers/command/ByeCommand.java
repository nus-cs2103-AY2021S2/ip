package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

public class ByeCommand extends Command {

    private static final boolean toExit = true;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        String message = Ui.getByeSuccess();
        return new CommandResponse(ByeCommand.class, message, ByeCommand.toExit);
    }
}
