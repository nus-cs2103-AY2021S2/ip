package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

public class InvalidCommand extends Command {
    public boolean execute(TaskList tasks, Ui ui, TaskStorage storage) {
        ui.print("Invalid command!");
        return true;
    }
}
