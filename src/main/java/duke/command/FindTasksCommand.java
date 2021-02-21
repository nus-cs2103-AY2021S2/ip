package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindTasksCommand extends Command {

    private static final Boolean toExit = false;

    private String keyword;

    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) throws DukeException {
        String message = Ui.getTaskList(tasks.findTasks(this.keyword));
        return new CommandResponse(message, FindTasksCommand.toExit);
    }
}
