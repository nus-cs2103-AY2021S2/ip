package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;
import pason.ui.Ui;

public class ByeCommand extends Command {
    /**
     * Initialises the ByeCommand.
     */
    public ByeCommand(String command) {
        super(command);
    }

    /**
     * Executes the command.
     */
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printMessage("Bye! I shall go rest now. PAge me when you need me!");
    }

    public boolean isExit() {
        return true;
    }
}
