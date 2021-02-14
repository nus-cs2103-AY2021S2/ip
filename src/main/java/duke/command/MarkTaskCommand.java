package duke.command;

import duke.*;

import java.io.IOException;

public class MarkTaskCommand extends Command {

    private int indexToMarkDone;

    public MarkTaskCommand(int indexToMarkDone) {
        this.indexToMarkDone = indexToMarkDone;
    }

    @Override
    public CommandResult execute(Ui ui, TaskList tasks, Storage storage) throws DukeStorageException {
        tasks.markTaskDone(indexToMarkDone);
        Task doneTask = tasks.get(indexToMarkDone);
        storage.saveTasks(tasks);
        String MarkTaskMessage = ui.generateMarkTaskMessage(doneTask);
        return new CommandResult(MarkTaskMessage,false);
    }
}
