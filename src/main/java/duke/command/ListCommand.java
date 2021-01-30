package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A ListCommand class to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructs ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the listing of tasks.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface.
     * @param storage  The storage handler.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
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
