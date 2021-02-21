package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

public class SaveTasksCommand extends Command {

    private static final boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        storage.save(tasks);

        String message = Ui.getSaveTaskListSuccess();
        return new CommandResponse(SaveTasksCommand.class,
                message, SaveTasksCommand.toExit);
    }
}
