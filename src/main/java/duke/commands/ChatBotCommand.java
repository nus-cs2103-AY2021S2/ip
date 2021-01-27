package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.Ui;
import duke.exceptions.ChatBotException;

public abstract class ChatBotCommand {
    public boolean isTerminated() {
        return false;
    }

    public abstract void runTask(Ui ui, TaskHandler th, Storage storage)
            throws ChatBotException;
}
