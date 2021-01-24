package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

public class InvalidCommand extends Command {
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        ui.print("Invalid command!");
    }
}
