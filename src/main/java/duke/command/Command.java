package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;

import java.io.IOException;

public abstract class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public Command(boolean isExit) {
        this.isExit = false;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeCommandException;

    public boolean isExit() {
        return this.isExit;
    }
}
