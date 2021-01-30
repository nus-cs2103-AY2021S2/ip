package commands;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import tasks.DukeTask;
import ui.Ui;

public class DoneCommand extends Command {
    private final int index;

    /**
     * Constructor for the Done Command.
     * @param index Index of the DukeTask we want to mark as done in the TaskList.
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the Done Command by marking the DukeTask in the TaskList as done.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     * @throws DukeException If index of the Task is out of bounds.
     */
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
