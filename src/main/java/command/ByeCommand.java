package command;

import Exceptions.DukeException;
import ui.Ui;
import storage.Storage;
import tasks.TaskList;

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
