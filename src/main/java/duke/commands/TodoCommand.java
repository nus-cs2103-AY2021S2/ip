package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;

/**
 * Represents a todo task command line
 */
public class TodoCommand extends ChatBotCommand {
    private String taskName;

    /**
     * Default constructor for the todoCommand class.
     *
     * @param taskName String that stores task name.
     */
    public TodoCommand(String taskName) {
        assert taskName.length() > 0 : "Error: you must enter a task name for the task";
        this.taskName = taskName;
    }

    /**
     * Returns message for adding a todo task.
     *
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @return Command response.
     * @throws ChatBotException if encounters error.
     */
    public String runTask(TaskHandler th, Storage storage) throws ChatBotException {
        th.addTodoTask(taskName);
        storage.writeToFile(th);
        String output = String.format(
                "Got it. I've added this task:\n%s\nNow you have %d task(s) in the list",
                th.getTaskList().get(th.getLength() - 1).toString(),
                th.getLength());
        return output;
    }
}
