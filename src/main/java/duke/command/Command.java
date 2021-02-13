package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected boolean isExit = false;

    public abstract CommandResult execute(Ui ui, TaskList tasks, Storage storage);

    public boolean shouldExit() {
        return isExit;
    }

}
