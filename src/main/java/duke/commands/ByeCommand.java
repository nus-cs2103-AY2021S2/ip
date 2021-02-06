package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
        ui.handleBye();
    }

    public boolean isExit() {
        return true;
    }
}
