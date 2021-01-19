package commands;

import duke.TaskManager;
import duke.Ui;
import exceptions.DukeException;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(Ui ui, TaskManager tm) throws DukeException;
}
