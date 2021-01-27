package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
        System.exit(0);
    }
}
