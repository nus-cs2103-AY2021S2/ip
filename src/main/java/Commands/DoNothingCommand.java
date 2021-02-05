package Commands;

import Tasks.TaskList;
import UserInterface.Ui;

public class DoNothingCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
    }

    public boolean isExit() {
        return false;
    }
}
