package duke.command;

import duke.Exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class showListCommand extends Command {

    public showListCommand(String description) {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getNumOfTasks() == 0) {
            throw new DukeException("There are currently no duke.tasks in your list.");
        }
        ui.displayList(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
