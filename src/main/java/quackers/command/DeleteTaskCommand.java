package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;
import quackers.task.Task;

public class DeleteTaskCommand extends Command {

    private static final boolean toExit = false;

    private int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        Task task = tasks.deleteTask(this.taskIndex);

        String message = Ui.getDeleteTaskSuccess(task);
        return new CommandResponse(DeleteTaskCommand.class,
                message, DeleteTaskCommand.toExit);
    }
}
