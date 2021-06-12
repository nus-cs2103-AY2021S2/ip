package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;


public abstract class Command {
    private final boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public Command(boolean isExit) {
        this.isExit = false;
    }

    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeCommandException;

    public boolean isExit() {
        return this.isExit;
    }
}
