package duke.command;

import duke.exceptions.DukeOutOfBoundsException;
import duke.exceptions.DukeStorageException;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.MessageGenerator;

public class MarkTaskCommand extends Command {

    private int indexToMarkDone;

    public MarkTaskCommand(int indexToMarkDone) {
        this.indexToMarkDone = indexToMarkDone;
    }

    @Override
    public CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage)
            throws DukeStorageException, DukeOutOfBoundsException {
        tasks.markTaskDone(indexToMarkDone);
        Task doneTask = tasks.get(indexToMarkDone);
        storage.saveTasks(tasks);
        String markTaskMessage = messageGenerator.generateMarkTaskMessage(doneTask);
        return new CommandResult(markTaskMessage, false);
    }
}
