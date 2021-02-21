package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SaveTasksCommand extends Command {

    private static final Boolean toExit = false;

    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) throws DukeException {
        storage.save(tasks);

        String message = Ui.getSaveTaskListSuccess();
        return new CommandResponse(SaveTasksCommand.class, message, SaveTasksCommand.toExit);
    }
}
