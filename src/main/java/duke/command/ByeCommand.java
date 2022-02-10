package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getByeMessage();
    }
}
