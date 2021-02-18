package bearbear.command;

import java.io.File;
import java.io.IOException;

import bearbear.bearbear.BearBear;
import bearbear.exceptions.InvalidArgumentException;
import bearbear.storage.Storage;

/**
 * Completes a task.
 */
public class DoneCommand extends Command {
    private static final String usageMessage = "Command: done <task_number>\n"
            + "Description: Marks a task as done\n";
    private final int taskNumber;

    /**
     * Creates a {@code DoneCommand} object with a task number component only.
     * @param taskNumber Index of task to be marked as done.
     */
    public DoneCommand(int taskNumber) {
        super("done");
        this.taskNumber = taskNumber;
    }

    /**
     * Returns index of task to be marked as done.
     * @return task index
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Validates argument of DoneCommand.
     * @param userInput User input as String.
     * @param bot A Duke object.
     * @return Index of task as int.
     * @throws InvalidArgumentException If task number is invalid.
     */
    public static int validateArgument(String userInput, BearBear bot) throws InvalidArgumentException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput);
        } catch (NumberFormatException ex) {
            throw new InvalidArgumentException("Invalid command! "
                    + "Please input task number using 'done (number)'.\n");
        }
        if (taskNumber > bot.getNumberOfTasks()) {
            throw new InvalidArgumentException("Please input argument <= to "
                    + bot.getNumberOfTasks() + "!\n");
        }
        return taskNumber;
    }

    public static String getUsageMessage() {
        return usageMessage;
    }

    @Override
    public String run(File file, BearBear bot) throws IOException {
        String output = bot.markAsDone(getTaskNumber());
        Storage.saveFile(file, bot);
        return output;
    }
}
