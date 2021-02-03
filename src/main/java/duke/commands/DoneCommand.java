package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;
import duke.tasks.Task;

/**
 * Represents a done command line
 */
public class DoneCommand extends ChatBotCommand {
    private int index;

    /**
     * Default constructor for the DoneCommand class.
     *
     * @param index the index of the task that is done in the list.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Returns message for completing a task.
     *
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @return Command response.
     * @throws ChatBotException if encounters error.
     */
    public String runTask(TaskHandler th, Storage storage) throws ChatBotException {
        Task task = th.markTaskAsDone(this.index);
        storage.writeToFile(th);
        String output = String.format("Nice! I've marked this task as done: \n%s",
                task.toString());
        return output;
    }
}
