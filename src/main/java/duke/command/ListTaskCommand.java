package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListTaskCommand extends Command {

    private static final String ERROR_MESSAGE = "Wait, you want me to list all the tasks or what?";

    public ListTaskCommand(String details) throws DukeException {
        if (!details.isBlank()) {
            throw new DukeException(ListTaskCommand.ERROR_MESSAGE);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printAllTasks(tasks);
        storage.saveTasksToFile(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
