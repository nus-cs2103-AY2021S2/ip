package Duke.Commands;

import Duke.Exceptions.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import java.io.IOException;

/**
 * An abstract class that represents all commands.
 */
public abstract class Command {

    public String execute(TaskList tasklist, Ui ui, Storage storage) throws IOException, DukeException {
        return "";
    }

    public boolean isExit() {
        return false;
    }
}
