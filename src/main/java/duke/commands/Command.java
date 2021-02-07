package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;


/**
 * A command class to represent all the commands/actions
 * recognised by the program.
 */
public abstract class Command {
    public final String description;

    Command(String description) {
        this.description = description;
    }

    /**
     * Returns true if the command ends the program.
     */
    public abstract boolean isEndOfProgram();

    /**
     * Abstract method to execute command
     *
     * @param taskList, the list of tasks
     * @param ui, the UI object
     * @param storage, the storage object
     * @return String stating what command has been executed
     * @throws DukeException, throws error warning for errors.
     */

    public abstract String execute(
            TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
