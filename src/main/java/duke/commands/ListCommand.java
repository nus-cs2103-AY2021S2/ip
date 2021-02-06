package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
        ui.handleList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
