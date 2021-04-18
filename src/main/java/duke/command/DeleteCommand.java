package duke.command;

import duke.exceptions.DukeOutOfBoundsException;
import duke.exceptions.DukeStorageException;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.MessageGenerator;

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
        String deleteMessage = messageGenerator.generateDeleteMessage(taskToDelete, tasks);
        return new CommandResult(deleteMessage, false);
    }
}
