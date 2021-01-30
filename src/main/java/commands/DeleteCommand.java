package commands;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import tasks.DukeTask;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for the Delete Command.
     * @param index Index of the DukeTask we want to delete from the TaskList.
     */
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
