package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;
import duke.exceptions.InvalidArgumentException;
import duke.storage.Storage;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Creates a {@code DeleteCommand} object with a task number component only.
     * @param taskNumber Index of task to be removed.
     */
    public DeleteCommand(int taskNumber) {
        super("delete");
        this.taskNumber = taskNumber;
    }

    /**
     * Returns index of task to be deleted.
     * @return task index
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    public static int validateArgument(String userInput, Duke bot) throws InvalidArgumentException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(userInput);
        } catch (NumberFormatException ex) {
            throw new InvalidArgumentException("Invalid command! "
                    + "Please input task number using 'done (number)'.\n");
        }
        if (taskIndex > bot.getNumberOfTasks()) {
            throw new InvalidArgumentException("Please input argument <= to "
                    + bot.getNumberOfTasks() + "!\n");
        }
        return taskIndex;
    }

    @Override
    public String run(File file, Duke bot) throws IOException {
        String output = bot.removeTask(getTaskNumber());
        Storage.saveFile(file, bot);
        return output;
    }
}
