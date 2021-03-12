package timmy.Commands;

import timmy.Exceptions.DukeException;
import timmy.Storage;
import timmy.TaskList;
import timmy.Ui;

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
