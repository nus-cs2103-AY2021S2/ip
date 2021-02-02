package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class ByeCommand extends Command {

    public ByeCommand(String description) {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.goodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
