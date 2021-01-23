package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;

public class ListCommand extends ChatBotCommand {
    public ListCommand() {
    }
    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        ui.printLine("Here are the tasks in your list:");
        ui.taskListLine(th.getTaskList());
    }

}
