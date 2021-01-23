package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;

public abstract class ChatBotCommand {
    public boolean isTerminated() {
        return false;
    }

    public abstract void runTask(Ui ui, TaskHandler th, Storage storage)
            throws ChatBotException;
}
