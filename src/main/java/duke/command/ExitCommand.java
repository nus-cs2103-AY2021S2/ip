package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    /**
     * Terminates the program and prints a farewell message to the user.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @param storage storage object
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }
}
