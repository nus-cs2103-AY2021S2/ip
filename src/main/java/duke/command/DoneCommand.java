package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;
import duke.storage.Storage;

/**
 * Completes a task.
 */
public class DoneCommand extends Command {
    private final String taskNumber;

    /**
     * Creates a {@code DoneCommand} object with a task number component only.
     * @param taskNumber Index of task to be marked as done.
     */
    public DoneCommand(String taskNumber) {
        super("done");
        this.taskNumber = taskNumber;
    }

    /**
     * Returns index of task to be marked as done.
     * @return task index
     */
    public String getTaskNumber() {
        return taskNumber;
    }

    @Override
    public String run(File file, Duke bot) throws IOException {
        String output = bot.markAsDone(getTaskNumber());
        Storage.saveFile(file, bot);
        return output;
    }
}
