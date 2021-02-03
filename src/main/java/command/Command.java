package main.java.command;

import main.java.TaskManager;
import main.java.Ui;

/**
 * A super abstract class for Command
 * with default empty constructor
 * execute(tm, ui) function to be override
 * and isExit() function to be inherited or override
 */
public abstract class Command {

    /**
     * Default empty Constructor of Command
     */
    public Command() {

    }

    /**
     * execute function for commands,
     * to be override by sub-classes
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     * @return execution result string
     */
    public abstract String execute(TaskManager tm, Ui ui);

    /**
     * Check if the command will call exit
     * @return true only if task is ExitCommand
     */
    public boolean isExit() {
        return false;
    }
}
