package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;
import chatbot.tasks.Task;

public class DoneCommand extends ChatBotCommand {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        Task task = th.taskIsDone(this.index);
        storage.writeToFile(th);
        ui.printLine("Nice! I've marked this task as done:");
        ui.printLine(task.toString());
    }
}
