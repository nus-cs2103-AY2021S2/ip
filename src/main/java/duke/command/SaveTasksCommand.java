package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class SaveTasksCommand extends Command {

    private static final boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        storage.save(tasks);

        String message = Ui.getSaveTaskListSuccess();
        return new CommandResponse(SaveTasksCommand.class,
                message, SaveTasksCommand.toExit);
    }
}
