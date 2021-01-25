package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;


public class TodoCommand extends ChatBotCommand {
    private String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        th.addTodoTask(taskName);
        storage.writeToFile(th);
        ui.printLine("Got it. I've added this task:");
        ui.printLine(th.getTaskList().get(th.getLength() - 1).toString());
        ui.printLine(String.format("Now you have %d task(s) in the list",
                th.getLength()));
    }
}
