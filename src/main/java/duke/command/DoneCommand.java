package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A DoneCommand class to perform task completion.
 */
public class DoneCommand extends Command {
    protected String fullCommand;

    /**
     * Constructs DoneCommand.
     *
     * @param fullCommand The command given.
     */
    public DoneCommand(String fullCommand) {
        super();
        this.fullCommand = fullCommand;
    }

    /**
     * Executes task completion.
     *
     * @param tasks The list of tasks.
     * @param storage  The storage handler.
     * @return Output for GUI.
     * @throws DukeException If user input format is wrong.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.done(this.fullCommand);
    }
}
