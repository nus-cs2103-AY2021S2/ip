package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;
import duke.tasks.Task;

/**
 * Represents a delete command line
 */
public class DeleteCommand extends ChatBotCommand {
    private int index;

    /**
     * Default constructor for the DeleteCommand class.
     *
     * @param index the index of the task to be deleted in the list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns message for deleting a task.
     *
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @return Command response.
     * @throws ChatBotException if encounters error.
     */
    public String runTask(TaskHandler th, Storage storage) throws ChatBotException {
        Task task = th.deleteTask(this.index);
        storage.writeToFile(th);
        String output = String.format("Noted. I've removed this task: \n%s\n Now you have %d task(s) in the list",
                task.toString(),
                th.getLength());
        return output;
    }
}
