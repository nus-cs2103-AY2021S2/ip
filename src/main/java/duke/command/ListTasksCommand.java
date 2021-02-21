package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListTasksCommand extends Command {

    private static final boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        String message = Ui.getTaskList(tasks.getList());
        return new CommandResponse(ListTasksCommand.class,
                message, ListTasksCommand.toExit);
    }
}
