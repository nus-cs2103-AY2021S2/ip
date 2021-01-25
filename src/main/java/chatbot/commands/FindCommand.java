package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;

public class FindCommand extends ChatBotCommand {
    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        ui.printLine("Here are the matching tasks in your list:");
        ui.taskListLine(th.findTask(this.keyWord));
    }
}
