package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.Exceptions.DukeException;
import duke.tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
