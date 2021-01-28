package Commands;

import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.DukeTask;
import Ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the Delete Command by removing the DukeTask into the TaskList.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     * @throws DukeException If index of the Task is out of bounds
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        try {
            DukeTask task = tasklist.getTask(this.index);
            tasklist.delete(this.index);
            ui.delete(task, tasklist.size());
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
