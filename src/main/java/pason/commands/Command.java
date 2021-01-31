package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;
import pason.ui.Ui;

public abstract class Command {
    private String command;
    public Command(String command) {
        this.command = command;
    }
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws Exception;

    public abstract boolean isExit();
}
