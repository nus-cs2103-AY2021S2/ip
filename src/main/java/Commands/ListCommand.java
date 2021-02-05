package Commands;

import Tasks.TaskList;
import UserInterface.Ui;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
        ui.handleList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
