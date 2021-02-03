package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
