package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @param ui       The user interface.
     * @param storage  The storage handler.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.find(this.fullCommand);
    }

    /**
     * Program does not exit.
     *
     * @return False to continue the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
