package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {

    private boolean exit;

    public Command() {
        this.exit = false;
    }

    public Command(boolean exit) {
        this.exit = exit;
    }

    public boolean isExit() {
        return exit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
