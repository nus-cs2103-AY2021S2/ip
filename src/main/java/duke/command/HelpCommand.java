package duke.command;

import duke.ui.Ui;

/**
 * Class containing data and methods specific to a HelpCommand.
 */
public class HelpCommand extends Command {
    /**
     * Displays all help instructions onto the GUI.
     */
    @Override
    public void process() {
        Ui.displayHelp();
    }
}
