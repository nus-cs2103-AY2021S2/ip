package duke.Command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

public abstract class Command {
    public abstract void execute(Ui ui, TaskList tasks, Storage storage);
}
