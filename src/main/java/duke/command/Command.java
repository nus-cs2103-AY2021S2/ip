package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract String execute(Ui ui, TaskManager tm, Storage st) throws DukeException;
}
