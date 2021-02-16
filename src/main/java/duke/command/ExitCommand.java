package duke.command;

import duke.ui.UI;

/**
 * Command class to exit program
 */
public class ExitCommand extends Command {

    /**
     * Create exit command
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
