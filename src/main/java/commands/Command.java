package commands;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;


public abstract class Command {

    Command() { }

    /**
     * Abstract method to Executes the Command given.
     *
     * @param tasklist Duke TaskList object.
     * @param storage Duke Storage object.
     * @param ui Duke UI object.
     * @throws DukeException Depends on the error of the command.
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
