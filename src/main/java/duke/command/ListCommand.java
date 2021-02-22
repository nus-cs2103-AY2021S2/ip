package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    private static final String ERROR_MSG = "Try typing 'list' only!";

    public ListCommand(String description) throws DukeException {
        if (!description.equals("")) {
            throw new DukeException(ERROR_MSG);
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.write(tasks);
        return ui.printTaskList(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
