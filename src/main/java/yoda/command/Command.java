package yoda.command;

import yoda.task.TaskList;
import yoda.ui.Ui;
import yoda.storage.Storage;

public abstract class Command {
    protected String[] details;
    protected Input taskType;
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public Command(String[] details) {
        this.details = details;
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute (TaskList taskList, Ui ui, Storage storage);
}
