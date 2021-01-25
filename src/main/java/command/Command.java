package command;

import ui.Ui;
import storage.Storage;
import Exceptions.DukeException;
import tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
