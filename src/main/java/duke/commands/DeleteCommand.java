package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.Ui;
import duke.exceptions.ChatBotException;
import duke.tasks.Task;

/**
 * Represents a delete command line
 */
public class DeleteCommand extends ChatBotCommand {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns message for deleting a task.
     *
     * @param ui Ui object.
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @throws ChatBotException if encounters error.
     */
    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        Task task = th.deleteTask(this.index);
        storage.writeToFile(th);
        ui.printLine("Noted. I've removed this task:");
        ui.printLine(task.toString());
        ui.printLine(String.format("Now you have %d task(s) in the list",
                th.getLength()));
    }
}
