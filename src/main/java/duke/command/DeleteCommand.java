package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Creates a new instance of <code>DeleteCommand</code>.
     *
     * @param description Task number to delete.
     */
    public DeleteCommand(String description) {
        this.type = "delete";
        this.description = description;
        this.isExit = false;
    }

    /**
     * Deletes specified task.
     *
     * @param tasks Task list.
     * @param ui UI.
     * @param storage Storage.
     * @throws IOException If there are input or output issues.
     * @throws DukeException If no task number was specified or task number specified is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        tasks.deleteTask(Integer.parseInt(description), storage);
    }

    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
