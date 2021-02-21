package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkTaskAsUndoneCommand extends Command {

    private static final Boolean toExit = false;

    private Integer taskIndex;

    public MarkTaskAsUndoneCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.markUndone(this.taskIndex);

        String message = Ui.getMarkUndoneTaskSuccess(task);
        return new CommandResponse(message, MarkTaskAsUndoneCommand.toExit);
    }
}
