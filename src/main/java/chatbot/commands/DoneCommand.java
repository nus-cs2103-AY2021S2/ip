package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;
import chatbot.tasks.Task;

/**
 * Represents a done command line
 */
public class DoneCommand extends ChatBotCommand {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Returns message for completing a task.
     *
     * @param ui Ui object.
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @throws ChatBotException if encounters error.
     */
    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        Task task = th.taskIsDone(this.index);
        storage.writeToFile(th);
        ui.printLine("Nice! I've marked this task as done:");
        ui.printLine(task.toString());
    }
}
