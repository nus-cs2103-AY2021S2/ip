package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkTaskAsDoneCommand extends Command {

    private static final Boolean toExit = false;

    private Integer taskIndex;

    public MarkTaskAsDoneCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.markDone(this.taskIndex);

        String message = Ui.getMarkDoneTaskSuccess(task);
        return new CommandResponse(message, MarkTaskAsDoneCommand.toExit);
    }
}
