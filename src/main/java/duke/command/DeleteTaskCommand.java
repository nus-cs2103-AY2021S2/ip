package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteTaskCommand extends Command {

    private static final Boolean toExit = false;

    private Integer taskIndex;

    public DeleteTaskCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(this.taskIndex);

        String message = ui.getDeleteTaskSuccess(task);
        return new CommandResponse(message, DeleteTaskCommand.toExit);
    }
}
