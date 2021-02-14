package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.storage.StorageManager;
import snom.model.task.TaskList;
import snom.ui.Snomio;

public abstract class Command {
    CommandEnum commandType;
    String content;

    public Command(CommandEnum commandType, String content){
        this.commandType = commandType;
        this.content = content;
    }

    public abstract CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException;
}
