package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

public class UsageCommand extends Command {

    private static final boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        String message = Ui.getUsage();
        return new CommandResponse(UsageCommand.class,
                message, UsageCommand.toExit);
    }
}
