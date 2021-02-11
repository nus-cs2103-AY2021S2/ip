package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
