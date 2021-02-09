package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand (int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskToDelete = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.showDeleteTask(taskToDelete, tasks);
            storage.saveFile(tasks.getTaskList());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
