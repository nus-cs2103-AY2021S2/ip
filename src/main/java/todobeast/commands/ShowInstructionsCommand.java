package todobeast.commands;

import todobeast.TaskList;
import todobeast.Ui;

public class ShowInstructionsCommand extends Command {

    public void execute(TaskList taskList, Ui ui) {
        ui.addToResponseOutput(ui.showInstructions());
    }
}
