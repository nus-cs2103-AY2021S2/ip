package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.commands.Command;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printIndentOutput("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
