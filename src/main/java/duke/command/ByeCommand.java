package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String command) {
        super.command = command;
        super.description = "";
        super.date = "";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        output = "Bye. Hope to see you again soon!";
        ui.response(output);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
