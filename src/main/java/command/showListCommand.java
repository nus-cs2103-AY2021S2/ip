package command;

import Exceptions.DukeException;
import ui.Ui;
import storage.Storage;
import tasks.TaskList;

public class showListCommand extends Command {

    public showListCommand(String description) {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getNumOfTasks() == 0) {
            throw new DukeException("There are currently no tasks in your list.");
        }
        ui.displayList(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
