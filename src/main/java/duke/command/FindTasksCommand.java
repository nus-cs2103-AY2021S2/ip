package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindTasksCommand extends Command {

    private static final boolean toExit = false;

    private String keyword;

    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        String message = Ui.getTaskList(tasks.findTasks(this.keyword));
        return new CommandResponse(FindTasksCommand.class,
                message, FindTasksCommand.toExit);
    }
}
