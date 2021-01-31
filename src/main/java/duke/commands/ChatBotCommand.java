package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;

public abstract class ChatBotCommand {
    public boolean isTerminated() {
        return false;
    }

    public abstract String runTask(TaskHandler th, Storage storage)
            throws ChatBotException;
}
