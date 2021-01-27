package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

public class ExitCommand extends Command {
    /**
     * Exits the program.
     * @param taskList
     * @param ui
     * @param storage
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
        System.exit(0);
    }
}
