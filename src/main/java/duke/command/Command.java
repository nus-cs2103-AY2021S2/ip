package duke.command;

import duke.tasks.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;


public abstract class Command {

    protected boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }

}
