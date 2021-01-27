package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

public class ListCommand extends Command {
    public boolean execute(TaskList tasks, Ui ui, TaskStorage storage) {
        ui.print(tasks);
        return true;
    }
}
