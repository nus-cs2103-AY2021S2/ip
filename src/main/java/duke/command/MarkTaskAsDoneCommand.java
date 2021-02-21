package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class MarkTaskAsDoneCommand extends Command {

    private static final boolean toExit = false;

    private int taskIndex;

    public MarkTaskAsDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        Task task = tasks.markDone(this.taskIndex);

        String message = Ui.getMarkDoneTaskSuccess(task);
        return new CommandResponse(MarkTaskAsDoneCommand.class,
                message, MarkTaskAsDoneCommand.toExit);
    }
}
