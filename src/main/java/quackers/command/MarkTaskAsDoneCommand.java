package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;
import quackers.task.Task;

public class MarkTaskAsDoneCommand extends Command {

    private static final boolean toExit = false;

    private int taskIndex;

    public MarkTaskAsDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        Task task = tasks.markDone(this.taskIndex);
        storage.save(tasks);

        String message = Ui.getMarkDoneTaskSuccess(task);
        return new CommandResponse(MarkTaskAsDoneCommand.class,
                message, MarkTaskAsDoneCommand.toExit);
    }
}
