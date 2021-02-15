package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getListMessage(tasks);
    }
}
