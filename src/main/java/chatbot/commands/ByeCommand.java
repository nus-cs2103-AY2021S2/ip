package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;

/**
 * Represents a terminating command line
 */
public class ByeCommand extends ChatBotCommand {
    public ByeCommand() {
    }

    /**
     * Returns terminating message of the chat bot.
     *
     * @param ui Ui object.
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @throws ChatBotException if encounters error.
     */
    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        ui.goodbye();
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
