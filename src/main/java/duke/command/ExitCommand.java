package duke.command;

import duke.Duke;
import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

public class ExitCommand extends Command {
    public boolean execute(TaskList tasks, Ui ui, TaskStorage storage) {
        ui.print("Goodbye. See you later!");
        return false;
    }
}
