package command;

import component.TaskList;
import component.Ui;
import component.Storage;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
