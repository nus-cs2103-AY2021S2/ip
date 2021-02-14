package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A DeleteCommand class to perform task deletion.
 */
public class DeleteCommand extends Command {
    protected String fullCommand;

    /**
     * Constructs DeleteCommand.
     *
     * @param fullCommand The command given.
     */
    public DeleteCommand(String fullCommand) {
        super();
        this.fullCommand = fullCommand;
    }

    /**
     * Executes task deletion.
     *
     * @param tasks The list of tasks.
     * @param storage  The storage handler.
     * @return Output for GUI.
     * @throws DukeException If user input format is wrong.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.delete(this.fullCommand);
    }
}
