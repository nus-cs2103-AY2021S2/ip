package duke.command;

import duke.Duke;
import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        Duke.toExit = true;
        ui.print("Goodbye. See you later!");
    }
}
