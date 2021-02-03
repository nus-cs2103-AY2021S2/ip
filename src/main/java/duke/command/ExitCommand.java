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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
