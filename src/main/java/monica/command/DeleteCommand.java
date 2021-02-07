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
     * Executes the command.
     * @param tasks The task list used for execution of the command.
     * @param ui Interactions with users.
     * @param storage Data stored in the local file path.
     * @throws MonicaException If there is any invalid command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MonicaException {
        if (id < 1 || id > tasks.getSize()) {
            throw new MonicaException("The task id is invalid.");
        }
        Task removed = tasks.removeTask(id);
        storage.updateFile();
        return ui.showDeleted(removed, tasks);
    }

    /**
     * Returns a boolean value to signal the chat bot to exit.
     * @return False as the command does not signal the bot to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
