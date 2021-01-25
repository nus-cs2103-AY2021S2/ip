package pason.commands;

import pason.storage.Storage;
import pason.tasks.TaskList;
import pason.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printMessage("I can't help you with this command yet. Sorry!");
    }

    public boolean isExit() {
        return false;
    }
}
