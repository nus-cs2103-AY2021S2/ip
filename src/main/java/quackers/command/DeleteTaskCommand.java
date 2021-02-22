package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.task.Task;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;

/**
 * Represents the command to delete a task.
 */
public class DeleteTaskCommand extends Command {

    private static final boolean toExit = false;

    private int taskIndex;

    /**
     * Constructs a DeleteTaskCommand object.
     *
     * @param taskIndex Index of the task to be deleted.
     */
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command and returns a response.
     *
     * @param tasks Core TaskList object.
     * @param storage Core Storage object.
     * @return CommandResponse object.
     * @throws QuackersException If specified invalid index, or unable to save data to disk.
     */
    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) throws QuackersException {
        Task task = tasks.deleteTask(this.taskIndex);
        storage.save(tasks);

        String message = Ui.getDeleteTaskSuccess(task);
        return new CommandResponse(DeleteTaskCommand.class, message, DeleteTaskCommand.toExit);
    }
}
