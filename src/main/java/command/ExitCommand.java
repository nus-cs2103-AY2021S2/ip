package main.java.command;

import main.java.TaskManager;
import main.java.Ui;

/**
 * Command to call exit of Duke
 */
public class ExitCommand extends Command {

    /**
     * Simply exit Duke
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     */
    @Override
    public void execute(TaskManager tm, Ui ui) {
        ui.displayExit();
    }

    /**
     * Check if the Command is ExitCommand
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
