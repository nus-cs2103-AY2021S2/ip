package main.java.command;

import main.java.TaskManager;
import main.java.Ui;

/**
 * WrongCommand to inform user of the issue
 */
public class WrongCommand extends Command {
    private String message;

    /**
     * Creates a WrongCommand with only general message
     */
    public WrongCommand() {

    }

    /**
     * Creates a WrongCommand with a particular reason message
     * @param message WrongCommand message
     */
    public WrongCommand(String message) {
        this.message = message;
    }

    /**
     * Call Ui to display error message
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     */
    @Override
    public void execute(TaskManager tm, Ui ui) {
        if (message != null) {
            ui.displayWrongCommand(message);
        } else {
            ui.displayWrongCommand();
        }
    }
}
