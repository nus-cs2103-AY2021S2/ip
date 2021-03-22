package marvin.commands;

import marvin.exception.DukeException;
import marvin.message.Messages;
import marvin.storage.Storage;
import marvin.task.TaskList;

/**
 * Represents a user command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Constructor.
     */
    public ListCommand() {
        super();
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

        return Messages.getListTaskMessage(tasks);
    }
}
