package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;
import quackers.task.Task;

public class MarkTaskAsUndoneCommand extends Command {

    private static final boolean toExit = false;

    private int taskIndex;

    public MarkTaskAsUndoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        Task task = tasks.markUndone(this.taskIndex);
        storage.save(tasks);

        String message = Ui.getMarkUndoneTaskSuccess(task);
        return new CommandResponse(MarkTaskAsUndoneCommand.class,
                message, MarkTaskAsUndoneCommand.toExit);
    }
}
