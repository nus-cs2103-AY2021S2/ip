package duke.command;

import java.io.IOException;

import duke.*;

public class DeleteCommand extends Command {

    private final int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public CommandResult execute(Ui ui, TaskList tasks, Storage storage) throws DukeStorageException {
        Task taskToDelete = tasks.pop(indexToDelete);
        storage.saveTasks(tasks);
        String deleteMessage = ui.generateDeleteMessage(taskToDelete,tasks);
        return new CommandResult(deleteMessage, false);
    }
}
