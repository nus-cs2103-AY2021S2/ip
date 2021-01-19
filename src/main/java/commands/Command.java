package duke;

import exceptions.DukeException;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(Ui ui, TaskManager tm) throws DukeException;
}
