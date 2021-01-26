package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A DeleteCommand class to perform task deletion.
 */
public class DeleteCommand extends Command {
    protected String fullCommand;

    /**
     * Constructs DeleteCommand.
     * @param fullCommand The command given.
     */
    public DeleteCommand(String fullCommand) {
        super();
        this.fullCommand = fullCommand;
    }

    /**
     * Executes task deletion.
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws DukeException If user input format is wrong.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.delete(this.fullCommand);
    }

    /**
     * Program does not exit.
     * @return False to continue the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
