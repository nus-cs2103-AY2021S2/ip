package duke.command;

import duke.component.TaskList;
import duke.component.Ui;
import duke.component.Storage;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
