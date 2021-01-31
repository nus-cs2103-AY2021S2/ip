package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskManager;
import duke.Ui;


/**
 * Represents a command keyed in by the user.
 */
public abstract class Command {
    protected String[] parsedCommand;

    protected Command(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Executes a specific valid command specified by the user.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     * @return String string to output to user
     * @throws IOException If the list of tasks are not in the correct storage format.
     * @throws DukeException If the task number specified to mark done or delete is not a valid task number.
     */
    public abstract String execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException, IOException;

    public boolean isExit() {
        return false;
    }
}
