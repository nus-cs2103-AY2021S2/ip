package marvin.commands;

import marvin.exception.DukeException;
import marvin.message.Messages;
import marvin.storage.Storage;
import marvin.task.Task;
import marvin.task.TaskList;

/**
 * Represents a user command to delete tasks in the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int index;

    /**
     * Constructor takes in the index of the task to be deleted from the task list.
     * @param index The 0-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the user command and returns a response message
     * @param tasks The state of the current task list.
     * @param storage The storage used by the program.
     * @return Response message.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        Task deletedTask = tasks.remove(index);
        storage.save(tasks);
        return Messages.getDeleteTaskMessage(deletedTask, tasks.size());
    }
}
