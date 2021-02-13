package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private final int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public CommandResult execute(Ui ui, TaskList tasks, Storage storage) {
        Task taskToDelete = tasks.delete(indexToDelete);
        try {
            storage.saveTasks(tasks);
        } catch (IOException err) {
            this.isExit = true;
        }
        String deleteMessage = ui.generateDeleteMessage(taskToDelete,tasks);
        return new CommandResult(deleteMessage, false);
    }
}
