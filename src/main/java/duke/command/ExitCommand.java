package duke.command;

import duke.ui.UI;

/**
 * Command class to exit the program
 */
public class ExitCommand extends Command {

    /**
     * Create an exit command
     */
    public ExitCommand() {
        super("exit");
    }

    /**
     * Display closing message to the user
     */
    @Override
    public String execute() {
        return UI.displayEndMessage();
    }
}
