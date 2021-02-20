package marvin.commands;

import marvin.storage.Storage;
import marvin.task.TaskList;

/**
 * Represents an incorrect user command. It will produce a feedback message to the user.
 */
public class IncorrectCommand extends Command {
    private String message;

    /**
     * Constructor takes in the feedback message to the user.
     * @param message The feedback message to the user.
     */
    public IncorrectCommand(String message) {
        super();
        this.message = message;
    }

    /**
     * Executes the user command and returns a response message
     * @param tasks The state of the current task list.
     * @param storage The storage used by the program.
     * @return Response message.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        assert storage != null;
        assert message != null;

        return message;
    }
}
