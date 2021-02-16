package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;

public abstract class Command {
    private String command;
    public Command(String command) {
        this.command = command;
    }
    public abstract CommandResult execute(TaskList tasks, Storage storage) throws Exception;

    public abstract boolean isExit();
}
