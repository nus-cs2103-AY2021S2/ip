package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(Ui ui, TaskList tasks, Storage storage);
    public boolean shouldExit() {
        return isExit;
    }

}
