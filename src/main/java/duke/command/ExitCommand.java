package command;

import component.Storage;
import component.TaskList;
import component.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
        System.exit(0);
    }
}
