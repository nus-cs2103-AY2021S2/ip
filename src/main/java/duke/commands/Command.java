package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public boolean isExit();
}
