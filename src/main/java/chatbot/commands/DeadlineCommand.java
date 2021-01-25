package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;

import java.time.LocalDate;

/**
 * Represents a deadline task command line
 */
public class DeadlineCommand extends ChatBotCommand {
    private String taskName;
    private LocalDate deadline;

    public DeadlineCommand(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    /**
     * Returns message for adding a deadline tasks.
     *
     * @param ui Ui object.
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @throws ChatBotException if encounters error.
     */

    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        th.addDeadlineTask(taskName, deadline);
        storage.writeToFile(th);
        ui.printLine("Got it. I've added this task:");
        ui.printLine(th.getTaskList().get(th.getLength() - 1).toString());
        ui.printLine(String.format("Now you have %d task(s) in the list",
                th.getLength()));
    }
}
