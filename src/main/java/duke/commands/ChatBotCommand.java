package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;

/**
 * Represents a ChatBotCommand line.
 */
public abstract class ChatBotCommand {
    public boolean isTerminated() {
        return false;
    }

    /**
     * Returns message for running tasks.
     *
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     */
    public abstract String runTask(TaskHandler th, Storage storage)
            throws ChatBotException;
}
