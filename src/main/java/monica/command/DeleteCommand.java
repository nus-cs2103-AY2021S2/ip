package monica.command;

import monica.MonicaException;
import monica.Storage;
import monica.task.Task;
import monica.task.TaskList;
import monica.ui.Ui;

/**
 * Represents a command that deletes task from the task list.
 */
public class DeleteCommand extends Command {
    private final int id;

    /**
     * Constructor for DeleteCommand class.
     * @param id ID of Task to be deleted.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * Executes delete command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MonicaException {
        if (id < 1 || id > tasks.getSize()) {
            throw new MonicaException("The delete index is invalid.");
        }
        Task removed = tasks.removeTask(id);
        storage.updateFile();
        return ui.showDeleted(removed, tasks);
    }
}
