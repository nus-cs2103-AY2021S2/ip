package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class SortCommand extends Command {

    public SortCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortListByDate();
        storage.save(tasks);
        return ui.getListMessage(tasks);
    }
}
