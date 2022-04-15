package duke.commands;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;



/**
 * Represents a deadline task command line
 */
public class DeadlineCommand extends ChatBotCommand {
    private String taskName;
    private LocalDate deadline;

    /**
     * Constructor
     * @param taskName String to store the task name.
     * @param deadline LocalDate to store the deadline.
     */
    public DeadlineCommand(String taskName, LocalDate deadline) {
        assert taskName.length() > 0 : "Error: you must enter a task name for the task";
        this.taskName = taskName;
        this.deadline = deadline;
    }

    /**
     * Returns message for adding a deadline tasks.
     *
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @return Command response.
     * @throws ChatBotException if encounters error.
     */
    public String runTask(TaskHandler th, Storage storage) throws ChatBotException {
        th.addDeadlineTask(taskName, deadline);
        storage.writeToFile(th);
        String output = String.format("Got it. I've added this task: \n%s\nNow you have %d task(s) in the list",
                th.getTaskList().get(th.getLength() - 1).toString(),
                th.getLength());
        return output;
    }
}
