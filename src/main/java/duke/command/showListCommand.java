package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class showListCommand extends Command {

    public showListCommand(String description) {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getNumOfTasks() == 0) {
            throw new DukeException("There are currently no duke.tasks in your list.");
        }

        String result = ui.displayList(tasks.getTasks());
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
