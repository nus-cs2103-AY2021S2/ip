package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

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
     * @throws DukeException If there is any invalid command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (id < 1 || id > tasks.getSize()) {
            throw new DukeException("The task id is invalid.");
        }
        tasks.getTask(id).markAsDone();
        ui.showDone(id, tasks);
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
