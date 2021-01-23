package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;
import chatbot.tasks.Task;

public class DeleteCommand extends ChatBotCommand {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        Task task = th.deleteTask(this.index);
        storage.writeToFile(th);
        ui.printLine("Noted. I've removed this task:");
        ui.printLine(task.toString());
        ui.printLine(String.format("Now you have %d task(s) in the list",
                th.getLength()));
    }
}
