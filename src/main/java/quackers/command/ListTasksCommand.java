package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

public class ListTasksCommand extends Command {

    private static final boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        String message = Ui.getTaskList(tasks.getTaskList());
        return new CommandResponse(ListTasksCommand.class,
                message, ListTasksCommand.toExit);
    }
}
