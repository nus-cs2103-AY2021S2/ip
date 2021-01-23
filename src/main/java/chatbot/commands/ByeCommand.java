package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;
import chatbot.tasks.Task;

public class ByeCommand extends ChatBotCommand {
    public ByeCommand() {
    }

    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        ui.goodbye();
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
