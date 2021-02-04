package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        ui.setDisplayMessage("Bye. Hope to see you again soon!");
        this.isExit = true;
    }
}
