package marvin.commands;

import marvin.exception.DukeException;
import marvin.message.Messages;
import marvin.storage.Storage;
import marvin.task.TaskList;

/**
 * Represents a user command which terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /*
     * Constructor. By default, exit status is true.
     */
    public ExitCommand() {
        this.isExit = true;
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

        storage.save(tasks);
        return Messages.MESSAGE_EXIT;
    }
}
