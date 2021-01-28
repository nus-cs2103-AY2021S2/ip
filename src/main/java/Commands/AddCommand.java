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

     /**
      * Executes the Add Command by adding the DukeTask into the TaskList.
      *
      * @param tasklist Duke TaskList object.
      * @param storage Duke Storage object.
      * @param ui Duke UI object.
      */
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
