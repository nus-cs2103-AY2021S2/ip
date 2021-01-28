package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public class DeleteCommand extends Command{
    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (id < 1 || id > tasks.getSize()) {
            throw new DukeException("The task id is invalid.");
        }
        Task removed = tasks.removeTask(id);
        ui.showDeleted(removed, tasks);
        storage.updateFile();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
