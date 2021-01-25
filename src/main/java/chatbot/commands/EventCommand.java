package chatbot.commands;

import chatbot.Storage;
import chatbot.TaskHandler;
import chatbot.Ui;
import chatbot.exceptions.ChatBotException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventCommand extends ChatBotCommand {
    private String taskName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventCommand(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void runTask(Ui ui, TaskHandler th, Storage storage) throws ChatBotException {
        th.addEventTask(taskName, startTime, endTime);
        storage.writeToFile(th);
        ui.printLine("Got it. I've added this task:");
        ui.printLine(th.getTaskList().get(th.getLength() - 1).toString());
        ui.printLine(String.format("Now you have %d task(s) in the list",
                th.getLength()));
    }
}
