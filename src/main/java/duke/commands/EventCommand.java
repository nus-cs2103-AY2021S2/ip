package duke.commands;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;

/**
 * Represents a event task command line
 */
public class EventCommand extends ChatBotCommand {
    private String taskName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor
     * @param taskName String to store the task name.
     * @param startTime LocalDate to store the starting time of the task.
     * @param endTime LocalDate to store the ending time of the task.
     */
    public EventCommand(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        assert taskName.length() > 0 : "Error: you must enter a task name for the task";
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns message for adding an event task.
     *
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @return Command response.
     * @throws ChatBotException if encounters error.
     */
    public String runTask(TaskHandler th, Storage storage) throws ChatBotException {
        th.addEventTask(taskName, startTime, endTime);
        storage.writeToFile(th);
        String output = String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list",
                th.getTaskList().get(th.getLength() - 1).toString(),
                th.getLength());
        return output;
    }
}
