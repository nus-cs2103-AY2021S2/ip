package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @param taskList The list of tasks.
     * @param ui       The user interface.
     * @param storage  The storage handler.
     * @throws DukeException If user input format is wrong.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.done(this.fullCommand);
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
