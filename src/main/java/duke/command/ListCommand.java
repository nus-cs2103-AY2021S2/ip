package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        boolean hasTask = tasks.size() != 0;
        return ui.getListTaskMessage(tasks.list(), hasTask);
    }

}
