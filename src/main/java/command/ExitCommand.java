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
     * @return command execution result string
     */
    @Override
    public String execute(TaskManager tm, Ui ui) {
        return ui.displayExit();
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
