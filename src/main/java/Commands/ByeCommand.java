package Commands;

import Tasks.TaskList;
import UserInterface.Ui;

public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
        ui.handleBye();
    }

    public boolean isExit() {
        return true;
    }
}
