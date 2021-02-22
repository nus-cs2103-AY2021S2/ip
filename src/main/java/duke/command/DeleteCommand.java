package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private final int index;

    private static final String NO_INDEX_ERROR = "Please specify a duke.task to delete!";
    private static final String INDEX_NOT_INT_ERROR = "Please specify a number instead!";
    private static final String OUT_OF_BOUND_ERROR = "Not in the list!";

    public DeleteCommand(String index) throws DukeException {
        if (index.isBlank()) {
            throw new DukeException(NO_INDEX_ERROR);
        }
        try {
            this.index = Integer.parseInt(index.trim());
        } catch (NumberFormatException e) {
            throw new DukeException(INDEX_NOT_INT_ERROR);
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task targetTask = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.write(tasks);
            return ui.getDeleteMsg(targetTask, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OUT_OF_BOUND_ERROR);
        }
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
