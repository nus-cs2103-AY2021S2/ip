package monica.command;

import monica.MonicaException;
import monica.Storage;
import monica.task.TaskList;
import monica.ui.Ui;

public class DoneCommand extends Command {
    private final int id;

    /**
     * Constructor for DoneCommand class.
     * @param id ID of Task that is done.
     */
    public DoneCommand(int id) {
        this.id = id;
    }

    /**
     * Executes done command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MonicaException {
        if (id < 1 || id > tasks.getSize()) {
            throw new MonicaException("The done index is out of bounds.");
        }
        tasks.getTask(id).markAsDone();
        storage.updateFile();
        return ui.showDone(id, tasks);
    }
}
