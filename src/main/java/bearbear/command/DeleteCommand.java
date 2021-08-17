package bearbear.command;

import java.io.File;
import java.io.IOException;

import bearbear.bearbear.BearBear;
import bearbear.exceptions.InvalidArgumentException;
import bearbear.storage.Storage;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    private static final String usageMessage = "Command: delete <task_number>\n"
            + "Description: Deletes a task\n";
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
     * @return Task index.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Validates argument of DeleteCommand.
     * @param userInput User Input as String.
     * @param bot A {@code BearBear} object.
     * @return Index of task as int.
     * @throws InvalidArgumentException If task index is invalid.
     */
    public static int validateArgument(String userInput, BearBear bot) throws InvalidArgumentException {
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

    public static String getUsageMessage() {
        return usageMessage;
    }

    @Override
    public String run(File file, BearBear bot) throws IOException {
        String output = bot.removeTask(getTaskNumber());
        Storage.saveFile(file, bot);
        return output;
    }
}
