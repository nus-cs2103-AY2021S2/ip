package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;

import java.time.LocalDate;

public class EventCommand extends ChatBotCommand {
    private String taskName;
    private String time;

    public EventCommand(String taskName, String time) {
        this.taskName = taskName;
        this.time = time;
    }

    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        th.addEventTask(taskName, time);
        storage.writeToFile(th);
        ui.printLine("Got it. I've added this task:");
        ui.printLine(th.getTaskList().get(th.getLength()).toString());
        ui.printLine(String.format("Now you have %d task(s) in the list",
                th.getLength()));
    }
}
