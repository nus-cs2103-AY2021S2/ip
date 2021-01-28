package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.Ui;

class ExitCommand extends Command{

    ExitCommand() {
        super(null, null, null,null, true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}
