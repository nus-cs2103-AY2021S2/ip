package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A ByeCommand class to exit program.
 */
public class ByeCommand extends Command {
    /**
     * Constructs ByeCommand.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Executes program exit.
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws DukeException If there is storage operation error.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        System.out.println("     Bye. Hope to see you again soon!");
        storage.clear();
        storage.save(taskList.getTaskList());
    }

    /**
     * Exits program.
     * @return True to exit program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
