package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class DoNothingCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
    }

    public boolean isExit() {
        return false;
    }
}
