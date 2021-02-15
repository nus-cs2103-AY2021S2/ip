package moomin.command;

import moomin.exception.DukeException;
import moomin.storage.Storage;
import moomin.task.TaskList;
import moomin.ui.Ui;

public abstract class Command {

    public abstract String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExitCommand();
}
