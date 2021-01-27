package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.DukeTask;
import Ui.Ui;

public class DoneCommand extends Command{
    private final int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        try {
            tasklist.done(this.index);
            DukeTask task = tasklist.getTask(this.index);
            ui.done(task);
            storage.save(tasklist);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("IndexOutOfBound");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
