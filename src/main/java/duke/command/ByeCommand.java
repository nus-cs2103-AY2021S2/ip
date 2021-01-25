package duke.command;

import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.byeCommandInteraction();
    }

    public boolean isExit() {
        return true;
    }
}
