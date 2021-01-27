package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.Ui;
import duke.exceptions.ChatBotException;

/**
 * Represents a todo task command line
 */
public class TodoCommand extends ChatBotCommand {
    private String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns message for adding a todo task.
     *
     * @param ui Ui object.
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @throws ChatBotException if encounters error.
     */
    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        th.addTodoTask(taskName);
        storage.writeToFile(th);
        ui.printLine("Got it. I've added this task:");
        ui.printLine(th.getTaskList().get(th.getLength() - 1).toString());
        ui.printLine(String.format("Now you have %d task(s) in the list",
                th.getLength()));
    }
}
