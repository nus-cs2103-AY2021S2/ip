package todobeast.commands;

import todobeast.Storage;
import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.ToDoBeastException;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws ToDoBeastException;

    public boolean isExit() {
        return isExit;
    }
}
