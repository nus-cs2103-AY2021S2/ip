package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
