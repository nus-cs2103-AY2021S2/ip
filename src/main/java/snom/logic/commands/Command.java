package snom.logic.commands;

import snom.common.exceptions.SnomException;
import snom.model.task.TaskList;
import snom.storage.StorageManager;
import snom.ui.Snomio;

/**
 * Represents a command with the ability to be executed.
 */
public abstract class Command {
    protected CommandEnum commandType;
    protected String content;

    /**
     * Constructs a {@code Command}
     *
     * @param commandType   command type
     * @param content       content of the command
     */
    public Command(CommandEnum commandType, String content) {
        this.commandType = commandType;
        this.content = content;
    }

    public abstract CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage)
            throws SnomException;
}
