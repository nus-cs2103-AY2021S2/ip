package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;
import duke.storage.Storage;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    private final String taskNumber;

    /**
     * Creates a {@code DeleteCommand} object with a task number component only.
     * @param taskNumber Index of task to be removed.
     */
    public DeleteCommand(String taskNumber) {
        super("delete");
        this.taskNumber = taskNumber;
    }

    /**
     * Returns index of task to be deleted.
     * @return task index
     */
    public String getTaskNumber() {
        return taskNumber;
    }

    @Override
    public String run(File file, Duke bot) throws IOException {
        String output = bot.removeTask(getTaskNumber());
        Storage.saveFile(file, bot);
        return output;
    }
}
