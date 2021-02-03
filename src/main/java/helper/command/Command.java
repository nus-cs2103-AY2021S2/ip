package helper.command;

import helper.DukeException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;

/**
 * Commands
 */
public abstract class Command {

    protected boolean isExit;

    public boolean isExit() {
        return isExit;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
