package duke.command;

import duke.Exceptions.DukeOutOfBoundsException;
import duke.Exceptions.DukeStorageException;
import duke.Model.TaskList;
import duke.Storage.Storage;
import duke.Tasks.Task;
import duke.Ui.MessageGenerator;

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
        String MarkTaskMessage = messageGenerator.generateMarkTaskMessage(doneTask);
        return new CommandResult(MarkTaskMessage,false);
    }
}
