package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.storage.StorageManager;
import snom.model.task.TaskList;
import snom.ui.Snomio;

/**
 * Represents a command with the ability to be executed.
 */
public abstract class Command {
    CommandEnum commandType;
    String content;

    public Command(CommandEnum commandType, String content){
        this.commandType = commandType;
        this.content = content;
    }

    public abstract CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException;
}
