package duke.command;

import duke.Exceptions.DukeOutOfBoundsException;
import duke.Exceptions.DukeStorageException;
import duke.Model.TaskList;
import duke.Storage.Storage;
import duke.Tasks.Task;
import duke.Ui.MessageGenerator;

public class DeleteCommand extends Command {

    private final int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage)
            throws DukeStorageException, DukeOutOfBoundsException {
        Task taskToDelete = tasks.pop(indexToDelete);
        storage.saveTasks(tasks);
        String deleteMessage = messageGenerator.generateDeleteMessage(taskToDelete,tasks);
        return new CommandResult(deleteMessage, false);
    }
}
