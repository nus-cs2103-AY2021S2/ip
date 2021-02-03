package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A FindCommand class to find tasks that contain a given keyword.
 */
public class FindCommand extends Command {
    protected String fullCommand;

    /**
     * Constructs FindCommand.
     *
     * @param fullCommand The command given.
     */
    public FindCommand(String fullCommand) {
        super();
        this.fullCommand = fullCommand;
    }

    /**
     * Executes task find.
     *
     * @param taskList The list of tasks.
     * @param storage  The storage handler.
     * @return Output for GUI.
     * @throws DukeException If user input format is wrong.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        return taskList.find(this.fullCommand);
    }
}
