package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.TaskList;
import ui.Snomio;

public abstract class Command {
    CommandEnum commandType;
    String content;

    public Command(CommandEnum commandType, String content){
        this.commandType = commandType;
        this.content = content;
    }

    public abstract CommandResponse execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException;
}
