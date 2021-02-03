package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return this.isExit;
    }
}
