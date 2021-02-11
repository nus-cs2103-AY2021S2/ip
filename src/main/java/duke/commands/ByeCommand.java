package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;

/**
 * Represents a terminating command line
 */
public class ByeCommand extends ChatBotCommand {
    /**
     * Default constructor for the ByeCommand class.
     */
    public ByeCommand() {
    }

    /**
     * Returns terminating message of the chat bot.
     *
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @return Command response.
     * @throws ChatBotException if encounters error.
     */
    public String runTask(TaskHandler th, Storage storage) throws ChatBotException {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
