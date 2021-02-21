package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListTasksCommand extends Command {

    private static final Boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) {
        String message = Ui.getTaskList(tasks.getList());
        return new CommandResponse(message, ListTasksCommand.toExit);
    }
}
