package duke.command;

import duke.Exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String description) {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.goodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
