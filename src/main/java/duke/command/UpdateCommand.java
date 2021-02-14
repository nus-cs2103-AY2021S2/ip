package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A UpdateCommand class to execute task update.
 */
public class UpdateCommand extends Command {
    protected String fullCommand;

    /**
     * Constructs UpdateCommand.
     *
     * @param fullCommand The command given.
     */
    public UpdateCommand(String fullCommand) {
        super();
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the task update.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage handler.
     * @return Output for GUI.
     * @throws DukeException If user input format is wrong.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.updateTask(this.fullCommand);
    }
}
