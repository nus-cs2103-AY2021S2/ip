package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class MarkTaskAsUndoneCommand extends Command {

    private static final boolean toExit = false;

    private int taskIndex;

    public MarkTaskAsUndoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        Task task = tasks.markUndone(this.taskIndex);

        String message = Ui.getMarkUndoneTaskSuccess(task);
        return new CommandResponse(MarkTaskAsUndoneCommand.class,
                message, MarkTaskAsUndoneCommand.toExit);
    }
}
