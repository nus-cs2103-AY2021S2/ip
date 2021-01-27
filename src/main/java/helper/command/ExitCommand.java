package helper.command;

import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Task;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.isExit = true;
        ui.dukePrint("Bye!!!");
    }

}
