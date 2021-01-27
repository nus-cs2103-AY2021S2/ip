package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.DukeTask;
import Ui.Ui;

public class AddCommand extends Command {
    DukeTask task;

    public AddCommand(DukeTask task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.add(this.task);
        ui.add(this.task, tasklist.size());
        storage.save(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
