package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

public class FindTasksCommand extends Command {

    private static final boolean toExit = false;

    private String keyword;

    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        String message = Ui.getTaskList(tasks.findTasks(this.keyword));
        return new CommandResponse(FindTasksCommand.class,
                message, FindTasksCommand.toExit);
    }
}
