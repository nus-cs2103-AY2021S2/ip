package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.speak("Goodbye for now, we will meet again.");
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
