package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.Ui;
import duke.exceptions.ChatBotException;

/**
 * Represents a list command line
 */
public class ListCommand extends ChatBotCommand {
    public ListCommand() {
    }

    /**
     * Returns list of tasks.
     *
     * @param ui Ui object.
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @throws ChatBotException if encounters error.
     */
    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        ui.printLine("Here are the tasks in your list:");
        ui.taskListLine(th.getTaskList());
    }

}
