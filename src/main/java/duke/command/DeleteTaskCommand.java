package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteTaskCommand extends Command {

    private static final boolean toExit = false;

    private int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        Task task = tasks.deleteTask(this.taskIndex);

        String message = Ui.getDeleteTaskSuccess(task);
        return new CommandResponse(DeleteTaskCommand.class,
                message, DeleteTaskCommand.toExit);
    }
}
