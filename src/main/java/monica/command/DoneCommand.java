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
        tasks.getTask(id).markAsDone();
        storage.updateFile();
        return ui.showDone(id, tasks);
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
