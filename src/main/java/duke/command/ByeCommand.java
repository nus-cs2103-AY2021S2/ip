package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.write(tasks);
        return ui.getExitMsg();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
