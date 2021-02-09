package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;
import duke.exceptions.InvalidArgumentException;
import duke.storage.Storage;

/**
 * Completes a task.
 */
public class DoneCommand extends Command {
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

    public static int validateArgument(String userInput, Duke bot) throws InvalidArgumentException {
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

    @Override
    public String run(File file, Duke bot) throws IOException {
        String output = bot.markAsDone(getTaskNumber());
        Storage.saveFile(file, bot);
        return output;
    }
}
