package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * Represents a command that deletes task from the task list.
 */
public class DeleteCommand extends Command{
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
     * @throws DukeException If there is any invalid command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (id < 1 || id > tasks.getSize()) {
            throw new DukeException("The task id is invalid.");
        }
        Task removed = tasks.removeTask(id);
        ui.showDeleted(removed, tasks);
        storage.updateFile();
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
