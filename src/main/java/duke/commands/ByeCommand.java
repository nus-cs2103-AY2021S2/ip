package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.Ui;
import duke.exceptions.ChatBotException;

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
